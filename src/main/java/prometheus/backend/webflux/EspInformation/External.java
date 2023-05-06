package prometheus.backend.webflux.EspInformation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    @CrossOrigin("*")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Position> getIp(@RequestParam("ip") String ip) {
        return this.positionService.getByIp(ip);
    }

    @PostMapping
    @CrossOrigin("*")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Position> create(@RequestBody ArrayList<Position> position) {
        return Flux.fromIterable(position).flatMapSequential(position1 -> {
            return this.positionService.findByName(position1.getName()).switchIfEmpty(this.positionService.createPosition(position1))
                    .flatMap(position2 -> {
                        position2.setCurrent(position1.getCurrent());
                        position2.setMode(position1.getMode());
                        position2.setTo_position(position1.getTo_position());
                        position2.setIp_address(position2.getIp_address());
                        position2.setPort(position2.getPort());
                        return this.positionService.updatePosition(position2);
                    })
                    .doOnError(throwable -> log.error(throwable.getMessage()));
        }).subscribeOn(parallel()).doOnError(throwable -> log.error(throwable.getMessage()));
    }
}
