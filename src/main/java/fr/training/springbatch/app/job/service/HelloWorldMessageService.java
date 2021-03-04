package fr.training.springbatch.app.job.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class HelloWorldMessageService {

	private final ServiceProperties serviceProperties;

	public HelloWorldMessageService(final ServiceProperties serviceProperties) {
		this.serviceProperties = serviceProperties;
	}

	public String message() {
		return serviceProperties.getMessage();
	}
}