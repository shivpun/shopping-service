package org.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "org.shopping.entity" })
@EnableJpaRepositories(basePackages = { "org.shopping.repository" })
public class ShoppingApplicationLocal {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplicationLocal.class, args);
	}
}
