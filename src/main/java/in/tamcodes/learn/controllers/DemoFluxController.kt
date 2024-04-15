package `in`.tamcodes.learn.controllers

import `in`.tamcodes.learn.dtoModel.Bank
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
@RequestMapping("/flux/apistream")
class DemoFluxController {

    val banks = mutableListOf(
        Bank("1234",1.2,1),
        Bank("12345",2.1,2),
        Bank("1235",3.1,20)
    )

    @GetMapping(value = ["/bankstream"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun getFluxStreamSample() : Flux<Bank>{
//        val sampleFlux = Flux.fromIterable(banks).delayElements(Duration.ofSeconds(3))

//        val sampleFlux = Flux.just(
//            Bank("1234",1.2,1),
//            Bank("12345",2.1,2),
//            Bank("1235",3.1,20)
//        ).delayElements(Duration.ofSeconds(3))

        return Flux.interval(Duration.ofSeconds(3))
            .map { banks[it.toInt()] }

//        return sampleFlux
    }

    @GetMapping("/bank")
    fun getBankSample() : MutableList<Bank>{
     return banks
    }


}