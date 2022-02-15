package com.sean.gateway.config

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.net.URI
import java.util.*

@Component
class LoggingFilter: GlobalFilter {

    private val log: Log = LogFactory.getLog(LoggingFilter::class.java)

    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
        val uris: Set<URI> = exchange!!.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, Collections.emptySet())
        val originalUri = if (uris.isEmpty()) "Unknown" else uris.iterator().next().toString()
        val route: Route? = exchange.getAttribute(GATEWAY_ROUTE_ATTR)
        val routeUri: URI? = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR)
        if (route != null) {
            log.info(
                "Incoming request " + originalUri + " is routed to id: " + route.id
                        + ", uri:" + routeUri
            )
        }
        return chain!!.filter(exchange)
    }
}