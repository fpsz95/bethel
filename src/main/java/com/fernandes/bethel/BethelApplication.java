package com.fernandes.bethel;

//import com.fernandes.bethel.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@EnableWebMvc
//@EnableAutoConfiguration //guess this annotation is interfering with datasource with hikari sth , check also u can add  **<spring-cloud.version>Greenwich.SR2</spring-cloud.version>** plz check
@SpringBootApplication //(exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories
		//(basePackageClasses = SocietyRepository.class)
public class BethelApplication extends SpringBootServletInitializer
{

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(BethelApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(BethelApplication.class, args);
	}

}
