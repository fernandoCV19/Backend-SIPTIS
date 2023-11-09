package backend.siptis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendSiptisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendSiptisApplication.class, args);
	}

}

