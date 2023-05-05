package prometheus.backend.webflux.EspInformation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import prometheus.backend.webflux.EspInformation.entity.Position;
import prometheus.backend.webflux.EspInformation.service.PositionService;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

import static reactor.core.scheduler.Schedulers.parallel;

@RestController
@RequestMapping("/api/v1/information")
@Slf4j
public class External {
    private final PositionService positionService;

    public External(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public Flux<Position> getIp(@RequestParam("ip") String ip) {
        return this.positionService.getByIp(ip);
    }

    @PostMapping
    public Flux<Position> create(@RequestBody ArrayList<Position> position) {
        return Flux.fromIterable(position).flatMapSequential(position1 -> {
            return this.positionService.findByName(position1.getName()).switchIfEmpty(this.positionService.createPosition(position1))
                    .flatMap(this.positionService::createPosition)
                    .doOnError(throwable -> log.error(throwable.getMessage()));
        }).subscribeOn(parallel()).doOnError(throwable -> log.error(throwable.getMessage()));
    }
}
