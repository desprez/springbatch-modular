package fr.training.springbatch.app.job;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DummyJob1Config.class, BatchTestConfiguration.class })
@SpringBatchTest
public class DummyJob1ConfigTest {

	@Autowired
	private JobLauncherTestUtils testUtils;

	@Autowired
	private Job job;

	@Test
	public void job1_launch_should_success() throws Exception {
		// Given
		testUtils.setJob(job);
		final JobParameters jobParameters = new JobParametersBuilder(testUtils.getUniqueJobParameters())
				.toJobParameters();
		// When
		final JobExecution jobExec = testUtils.launchJob(jobParameters);
		// Then
		assertThat(jobExec.getJobInstance().getJobName(), equalTo("dummy-job-1"));
		assertThat(jobExec.getStatus(), equalTo(BatchStatus.COMPLETED));
	}
}
