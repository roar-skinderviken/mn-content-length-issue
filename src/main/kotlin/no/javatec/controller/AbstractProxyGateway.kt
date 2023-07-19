package no.javatec.controller

import io.micronaut.http.*
import io.micronaut.http.client.ProxyHttpClient
import org.reactivestreams.Publisher

/**
 * Base class for proxying requests to external APIs.
 *
 * @property proxyHttpClient [ProxyHttpClient] instance.
 * @property controllerPath Path of the controller. E.g. '/gateway'.
 * @property remoteHostAndPort Host and port of the remote API. E.g. 'localhost:8081'.
 * @property scheme Scheme of the remote API. E.g. 'http'.
 */
abstract class AbstractProxyGateway(
    private val proxyHttpClient: ProxyHttpClient,
    private val controllerPath: String,
    private val remoteHostAndPort: String,
    private val scheme: String
) {
    /**
     * Proxy request to external API.
     *
     * @param request [HttpRequest] instance.
     * @return [Publisher] of [MutableHttpResponse].
     */
    internal fun proxyRequest(
        request: HttpRequest<*>
    ): Publisher<MutableHttpResponse<*>> = proxyHttpClient.proxy(mutateForProxying(request))

    /**
     * Mutate request for proxying. Used by [proxyRequest].
     *
     * @param request [HttpRequest]
     * @return [MutableHttpRequest]
     */
    private fun mutateForProxying(
        request: HttpRequest<*>
    ): MutableHttpRequest<out Any> = request.mutate().uri { uriBuilder ->
        uriBuilder
            .scheme(scheme)
            .replacePath(request.path.replace(controllerPath, ""))
            .host(remoteHostAndPort)
    }
}