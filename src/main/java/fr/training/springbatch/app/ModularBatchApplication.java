package fr.training.springbatch.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.ListableJobLocator;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

import fr.training.springbatch.app.job.DummyJob1Config;
import fr.training.springbatch.app.job.DummyJob2Config;

@SpringBootApplication
@EnableBatchProcessing(modular = true)
public class ModularBatchApplication {

	private static final Logger logger = LoggerFactory.getLogger(ModularBatchApplication.class);

	@Autowired
	private ListableJobLocator jobLocator;

	public static void main(final String[] args) {
		final ConfigurableApplicationContext ctx = SpringApplication.run(ModularBatchApplication.class, args);

		showUsage(ctx.getEnvironment());

		// Exit with system error code expected
		System.exit(SpringApplication.exit(ctx));
	}

	private static void showUsage(final ConfigurableEnvironment environment) {
		final String jobName = environment.getProperty("spring.batch.job.names");
		if (StringUtils.isEmpty(jobName)) {
			logger.error("Missing job name");
			logger.warn(
					"Usage : \n\tjava -Dspring.batch.job.names=dummy-job-2 -jar springbatch-modular.jar parameter1=value1 parameter2=value2 ...\n");
		}
	}

	public void displayAvailableJobNames() {
		logger.warn("available Jobs:");
		jobLocator.getJobNames().forEach(s -> logger.warn(s));
	}

	@Bean
	public ApplicationContextFactory getDummy1Ctx() {
		return new GenericApplicationContextFactory(DummyJob1Config.class);
	}

	@Bean
	public ApplicationContextFactory getDummy2Ctx() {
		return new GenericApplicationContextFactory(DummyJob2Config.class);
	}
}
