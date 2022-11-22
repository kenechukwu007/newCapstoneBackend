package com.ecommerce.akatsukiresources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
public class AkatsukiResourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkatsukiResourcesApplication.class, args);
	}

}
