package com.changer.billcoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Build an executable JAR
 * You can run the application from the command line with Gradle or Maven. You can also build a single executable JAR file that contains all the necessary dependencies, classes, and resources and run that. Building an executable jar makes it easy to ship, version, and deploy the service as an application throughout the development lifecycle, across different environments, and so forth.
 *
 * If you use Gradle, you can run the application by using
 * 		./gradlew bootRun
 * Alternatively, you can build the JAR file by using
 * 		./gradlew build
 * and then run the JAR file, as follows:
 *
 * 		java -jar build/libs/gs-rest-service-0.1.0.jar
 * If you use Maven, you can run the application by using
 * 		./mvnw spring-boot:run
 * Alternatively, you can build the JAR file with
 * 		./mvnw clean package
 * and then run the JAR file, as follows:
 *
 * 		java -jar target/gs-rest-service-0.1.0.jar
 */
@SpringBootApplication
public class BillcoinChangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillcoinChangerApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/change").allowedOrigins("http://localhost:8080");
			}
		};
	}
}