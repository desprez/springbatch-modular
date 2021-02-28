package fr.training.springbatch.app.commons;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public abstract class AbstractJobConfiguration {

	@Autowired
	protected JobBuilderFactory jobBuilderFactory;

	@Autowired
	protected StepBuilderFactory stepBuilderFactory;

	public AbstractJobConfiguration() {
		super();
	}

	/**
	 * Display report at the end of the job
	 */
	@Bean
	public JobReportListener reportListener() {
		return new JobReportListener();
	}

}