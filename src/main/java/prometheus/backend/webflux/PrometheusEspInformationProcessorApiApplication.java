package prometheus.backend.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication
public class PrometheusEspInformationProcessorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrometheusEspInformationProcessorApiApplication.class, args);
	}

}
