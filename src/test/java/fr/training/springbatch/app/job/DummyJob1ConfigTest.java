package fr.training.springbatch.app.job;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.training.springbatch.app.BatchTestConfiguration;

@ActiveProfiles("test") // used to load according tests properties (ie datasource properties)
@RunWith(SpringRunner.class)
@SpringBatchTest // used to register JobLauncherTestUtils, JobRepositoryTestUtils & listeners
@SpringBootTest(classes = { BatchTestConfiguration.class //
		, DummyJob1Config.class // import job definition to ensure only one job in the context
}, properties = "spring.batch.job.enabled=false" // prevent job launch twice
		)
public class DummyJob1ConfigTest {

	@Autowired
	private JobLauncherTestUtils testUtils;

	@Test
	public void job1_launch_should_success() throws Exception {
		// Given
		final JobParameters jobParameters = new JobParametersBuilder(testUtils.getUniqueJobParameters())
				.toJobParameters();
		// When
		final JobExecution jobExec = testUtils.launchJob(jobParameters);
		// Then
		assertThat(jobExec.getJobInstance().getJobName()).isEqualTo("dummy-job-1");
		assertThat(jobExec.getStatus()).isEqualTo(BatchStatus.COMPLETED);
	}
}
