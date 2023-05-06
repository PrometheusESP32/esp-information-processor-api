package prometheus.backend.webflux.EspInformation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import prometheus.backend.webflux.EspInformation.repository.PositionRepository;
import prometheus.backend.webflux.EspInformation.entity.Position;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
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

    public Mono<Position> updatePosition(Position position) {
        return this.findByName(position.getName()).flatMap(position1 -> {
            position1.setTo_position(position.getTo_position());
            position1.setCurrent(position.getCurrent());
            position1.setMode(position.getMode());
            position1.setName(position.getName());
            position1.setIp_address(position.getIp_address());
            position1.setPort(position.getPort());
            position1.setMotor_id(position.getMotor_id());
            return this.createPosition(position1);
        }).doOnError(Throwable -> log.error(Throwable.getMessage()));
    }

    public Mono<Position> findByName(String name) {
        return this.positionRepository.findByName(name);
    }
}
