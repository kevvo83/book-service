package com.kevpersonal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan
@SpringBootApplication
public class RestServiceApplication {

	private static ApplicationContext appContext;

	public static void main(String[] args) {

		appContext = SpringApplication.run(RestServiceApplication.class, args);
		checkBeansPresence(
				"HibernateConfig", "sessionFactory", "BookRepositoryImpl"
		);
	}

	private static void checkBeansPresence(String... beans) {
		for (String beanName : beans) {
			System.out.println("Is " + beanName + " in ApplicationContext: " +
					appContext.containsBean(beanName));
		}
	}

}
