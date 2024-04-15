package in.tamcodes.learn.controllers;

import in.tamcodes.learn.dtoModel.Bank;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/javaflux/apistream")
public class DemoFluxControllerJava {

    @GetMapping(value = "/bankstream")
    public Flux<Bank> banks(){
        return Flux.just(
                new Bank("1",1,1),
                new Bank("2",2,2),
                new Bank("3",3,3)
        ).delayElements(Duration.ofSeconds(3));

    }

    @GetMapping(value = "/stringstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> strings(){
        return Flux.just(
                "a","b","c"
        ).delayElements(Duration.ofSeconds(3));
    }
}
