package fr.training.springbatch.app;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Test base configuration for all job tests.
 */
@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
@ComponentScan("fr.training.springbatch.app.job.service")
public class BatchTestConfiguration {

	/**
	 * Define JdbcTemplate can be used for checking if DB records has been processed well.
	 *
	 * @param dataSource injected by spring
	 * @return a jdbcTemplate
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}