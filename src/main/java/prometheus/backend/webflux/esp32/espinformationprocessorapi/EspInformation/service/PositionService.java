package prometheus.backend.webflux.esp32.espinformationprocessorapi.EspInformation.service;

import org.springframework.stereotype.Service;
import prometheus.backend.webflux.esp32.espinformationprocessorapi.EspInformation.entity.Position;
import prometheus.backend.webflux.esp32.espinformationprocessorapi.EspInformation.repository.PositionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PositionService {

    private final PositionRepository positionRepository;


    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Flux<Position> getByIp(String ip) {
        return this.positionRepository.findByIpAddress(ip);
    }

    public Mono<Position> createPosition(Position position) {
        return this.positionRepository.save(position);
    }

    public Mono<Position> findByName(String name) {
        return this.positionRepository.findByName(name);
    }
}
