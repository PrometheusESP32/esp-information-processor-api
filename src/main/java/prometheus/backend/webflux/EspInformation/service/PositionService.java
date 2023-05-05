package prometheus.backend.webflux.EspInformation.service;

import org.springframework.stereotype.Service;
import prometheus.backend.webflux.EspInformation.repository.PositionRepository;
import prometheus.backend.webflux.EspInformation.entity.Position;
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
