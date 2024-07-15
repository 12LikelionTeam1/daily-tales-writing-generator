package net.likelion.dailytales.config

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class CustomJwtFilter(
    private val jwtTokenVerifier: JwtTokenVerifier
) : WebFilter {
    override fun filter(
        exchange: ServerWebExchange,
        chain: WebFilterChain
    ): Mono<Void> {
        if (exchange.request.headers.containsKey("Authorization")) {
            val token = exchange.request.headers["Authorization"]!!.first()
            if (runCatching { jwtTokenVerifier.verify(token) }.isFailure) {
                exchange.response.statusCode = HttpStatus.UNAUTHORIZED
                return exchange.response.setComplete()
            }
        } else {
            exchange.response.statusCode = HttpStatus.UNAUTHORIZED
            return exchange.response.setComplete()
        }
        return chain.filter(exchange)
    }
}