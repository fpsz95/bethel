package com.fernandes.bethel;

//import com.fernandes.bethel.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
		//(basePackageClasses = SocietyRepository.class)
public class BethelApplication {

	public static void main(String[] args) {
		SpringApplication.run(BethelApplication.class, args);
	}

}
