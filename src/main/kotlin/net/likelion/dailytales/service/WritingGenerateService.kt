package net.likelion.dailytales.service

import kotlinx.coroutines.reactor.awaitSingle
import net.likelion.dailytales.service.dto.OpenAIRequest
import net.likelion.dailytales.service.dto.OpenAIResponse
import net.likelion.dailytales.service.dto.Message
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class WritingGenerateService(
    @Value("\${openai.model}") private val model: String,
    @Value("\${openai.api.key}") private val openAiKey: String,
    @Value("\${openai.api.url}") private val openAiUrl: String,

    private val webClient: WebClient,
) {
    suspend fun generateWriting(prompt: String): Message {
        val response = webClient
            .post()
            .uri(openAiUrl)
            .headers {
                it.setBearerAuth(openAiKey)
            }
            .bodyValue(OpenAIRequest.of(model = model, prompt = prompt))
            .retrieve()
            .bodyToMono(OpenAIResponse::class.java)
            .awaitSingle()
        return response.choices.first().message
    }
}