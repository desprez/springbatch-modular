package fr.training.springbatch.app.job;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableBatchProcessing
public class BatchTestConfiguration {

	@Bean
	public JobLauncherTestUtils jobLauncherTestUtils() {
		return new JobLauncherTestUtils();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	//	@Bean
	//	public DataSource dataSource() {
	//		return new EmbeddedDatabaseBuilder() //
	//				.setType(EmbeddedDatabaseType.H2) //
	//				.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
	//				.addScript("classpath:org/springframework/batch/core/schema-h2.sql") //
	//				.build();
	//	}

}