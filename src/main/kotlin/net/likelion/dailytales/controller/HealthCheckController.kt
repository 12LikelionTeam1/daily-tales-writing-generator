package net.likelion.dailytales.controller

import net.likelion.dailytales.controller.dto.HealthCheckResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {
    @GetMapping("/api/ai/health")
    fun healthCheck(): HealthCheckResponse {
        return HealthCheckResponse("ok")
    }
}