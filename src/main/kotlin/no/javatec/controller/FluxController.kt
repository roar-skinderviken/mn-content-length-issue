package no.javatec.controller

import io.micronaut.http.HttpHeaders.LINK
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import no.javatec.bridge.FluxBridge
import reactor.core.publisher.Flux

@Controller("/flux-test")
class FluxController(private val fluxBridge: FluxBridge) {

    @Get(produces = [APPLICATION_JSON])
    fun get(): HttpResponse<Flux<Int>> = HttpResponse.ok(fluxBridge.getFlux()).apply {
        headers[LINK] = "<https://example.org>; rel=\"index\""
    }
}