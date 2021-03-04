package fr.training.springbatch.app.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import fr.training.springbatch.app.commons.AbstractJobConfiguration;
import fr.training.springbatch.app.job.service.HelloWorldMessageService;

/**
 * Simple job configuration to illustrate modular = true
 */
public class DummyJob2Config extends AbstractJobConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(DummyJob2Config.class);

	@Autowired
	private HelloWorldMessageService  service;

	@Bean
	public Step step() {
		return stepBuilderFactory.get("dummy-step-2") //
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext)
							throws Exception {

						logger.info(service.message());

						return RepeatStatus.FINISHED;
					}
				}).listener(reportListener()) //
				.build();
	}

	@Bean
	public Job getJob() {
		return jobBuilderFactory.get("dummy-job-2") //
				.incrementer(new RunIdIncrementer())
				.start(step()) //
				.listener(reportListener()) //
				.build();
	}
}