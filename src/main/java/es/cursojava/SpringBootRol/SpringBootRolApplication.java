package es.cursojava.SpringBootRol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {"es.cursojava.SpringBootRol", "controllers", "service", "utilidades"})
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "entities")
public class SpringBootRolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRolApplication.class, args);
	}

}
