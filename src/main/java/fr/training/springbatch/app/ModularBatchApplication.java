package fr.training.springbatch.app;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.training.springbatch.DummyJob1Config;
import fr.training.springbatch.DummyJob2Config;

@SpringBootApplication
@EnableBatchProcessing(modular = true)
public class ModularBatchApplication {

	public static void main(final String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(ModularBatchApplication.class, args)));
	}

	@Bean
	public ApplicationContextFactory getDummy1() {
		return new GenericApplicationContextFactory(DummyJob1Config.class);
	}

	@Bean
	public ApplicationContextFactory getDummy2() {
		return new GenericApplicationContextFactory(DummyJob2Config.class);
	}
}
