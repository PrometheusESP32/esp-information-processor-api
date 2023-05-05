package prometheus.backend.webflux.esp32.espinformationprocessorapi.EspInformation.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import prometheus.backend.webflux.esp32.espinformationprocessorapi.EspInformation.entity.Position;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PositionRepository extends ReactiveCrudRepository<Position, Long> {
    Mono<Position> findByName(String name);

    @Query("SELECT * FROM position WHERE ip_address = $1")
    Flux<Position> findByIpAddress(String ip_address);
}
