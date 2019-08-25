package org.shopping.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
@EntityScan(basePackages = { "org.shopping.entity" })
@EnableJpaRepositories(basePackages = { "org.shopping.repository" })
@ImportResource(locations = "context/application.xml")
public class ShoppingApplicationLocal {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplicationLocal.class, args);
	}
}
