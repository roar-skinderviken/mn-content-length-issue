package no.javatec.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.ProxyHttpClient
import org.reactivestreams.Publisher

private const val GATEWAY_BASE_URL = "/gateway"

@Controller(GATEWAY_BASE_URL)
class GatewayController(
    proxyHttpClient: ProxyHttpClient,
) : AbstractProxyGateway(
    proxyHttpClient = proxyHttpClient,
    controllerPath = GATEWAY_BASE_URL,
    remoteHostAndPort = "localhost:8080",
    scheme = "http"
) {
    /**
     * Proxy requests from port 8080 to port 8081.
     *
     * @param request [HttpRequest] instance.
     * @return [Publisher] of [MutableHttpResponse].
     */
/*
    @Get(value = "/health/liveness")
    fun barnBvrApiGetStatus(request: HttpRequest<*>): Publisher<MutableHttpResponse<*>> = proxyRequest(request)
*/

    @Get(value = "/flux-test")
    fun barnBvrApiGetStatus(request: HttpRequest<*>): Publisher<MutableHttpResponse<*>> = proxyRequest(request)

}