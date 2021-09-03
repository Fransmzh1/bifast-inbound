package bifast.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"bifast.library.model"})
@EnableJpaRepositories(basePackages = {"bifast.library.repository"} )
public class BifastAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BifastAdminApplication.class, args);
	}

}