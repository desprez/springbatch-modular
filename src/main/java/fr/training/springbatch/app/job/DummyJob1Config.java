package fr.training.springbatch.app.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;

import fr.training.springbatch.app.commons.AbstractJobConfiguration;

public class DummyJob1Config extends AbstractJobConfiguration {

	@Bean
	public Step step() {
		return stepBuilderFactory.get("dummy-step-1") //
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext)
							throws Exception {
						return RepeatStatus.FINISHED;
					}
				}).listener(reportListener()) //
				.build();
	}

	@Bean
	public Job getJob() {
		return jobBuilderFactory.get("dummy-job-1") //
				.start(step()) //
				.listener(reportListener()) //
				.build();
	}

}