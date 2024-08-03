package net.likelion.dailytales.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter
import org.springframework.web.cors.reactive.CorsConfigurationSource

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {
    @Bean
    fun springSecurityFilterChain(
        http: ServerHttpSecurity,
        objectMapper: ObjectMapper,
        corsConfigurationSource: CorsConfigurationSource
    ): SecurityWebFilterChain = http
        .csrf { it.disable() }
        .cors { it.configurationSource(corsConfigurationSource) }
        .formLogin { it.disable() }
        .httpBasic { it.disable() }
        .anonymous { it.disable() }
        .logout { it.disable() }
        .securityContextRepository(NoOpServerSecurityContextRepository.getInstance()) //session STATELESS
        .headers { headerSpec ->
            headerSpec.frameOptions {
                it.mode(XFrameOptionsServerHttpHeadersWriter.Mode.SAMEORIGIN)
            }
        }
        .authorizeExchange {
            it.pathMatchers("/api/ai/**").permitAll()
            it.anyExchange().denyAll()
        }
        .build()
}