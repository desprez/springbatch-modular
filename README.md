# springbatch-modular

springbatch-modular springboot application demo who demonstrate usage of modular batch with tests @EnableBatchProcessing(modular = true).

2 jobs
- DummyJob1Config
- DummyJob2Config

Job config must be on package beyond the application (@SpringbootApplication) package.


```java
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
```
