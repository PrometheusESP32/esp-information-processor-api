package prometheus.backend.webflux.esp32.espinformationprocessorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication
public class EspInformationProcessorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EspInformationProcessorApiApplication.class, args);
	}

}
