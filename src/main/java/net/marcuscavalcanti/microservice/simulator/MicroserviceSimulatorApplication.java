package net.marcuscavalcanti.microservice.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan("net.marcuscavalcanti.*")
@EnableAutoConfiguration
@EnableAsync
public class MicroserviceSimulatorApplication  {
	
	    
    public static void main(String[] args) {
		SpringApplication.run(MicroserviceSimulatorApplication.class, args);
	}
    
	
	
}
