package net.likelion.dailytales.service

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactor.awaitSingle
import net.likelion.dailytales.service.dto.OpenAIRequest
import net.likelion.dailytales.service.dto.OpenAIResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class KeywordsExtractService(
    @Value("\${openai.model}") private val model: String,
    @Value("\${openai.api.key}") private val openAiKey: String,
    @Value("\${openai.api.url}") private val openAiUrl: String,
    @Value("\${app.main-keywords-prompt}") private val prompt: String,

    private val objectMapper: ObjectMapper,
    private val webClient: WebClient
) {
    suspend fun extractKeywordsFrom(writings: String): Keywords {
        val response = webClient
            .post()
            .uri(openAiUrl)
            .headers {
                it.setBearerAuth(openAiKey)
            }
            .bodyValue(OpenAIRequest.of(model = model, prompt = "$prompt $writings"))
            .retrieve()
            .bodyToMono(OpenAIResponse::class.java)
            .awaitSingle()
        val result = response.choices.first().message
        return objectMapper.readValue(result.content, Keywords::class.java)
    }
}