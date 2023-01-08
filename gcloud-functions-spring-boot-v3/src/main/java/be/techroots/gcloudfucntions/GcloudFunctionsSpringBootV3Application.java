package be.techroots.gcloudfucntions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class GcloudFunctionsSpringBootV3Application {

	public static void main(String[] args) {
		SpringApplication.run(GcloudFunctionsSpringBootV3Application.class, args);
	}

	@Bean
	public Function<String, String> uppercase() {
		return String::toUpperCase;
	}
}
