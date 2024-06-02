package no.javatec

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("/gateway")
interface GatewayClient {

    @Get("/health/liveness")
    fun getLiveness(): String
}