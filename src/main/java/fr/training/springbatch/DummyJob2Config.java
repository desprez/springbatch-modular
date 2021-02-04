package fr.training.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyJob2Config {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step step() {
		return stepBuilderFactory.get("dummy-step-2").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext)
					throws Exception {
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Job getJob() {
		return jobBuilderFactory.get("dummy-job-2").start(step()).build();
	}
}