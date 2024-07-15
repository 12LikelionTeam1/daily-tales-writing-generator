package net.likelion.dailytales.controller

import net.likelion.dailytales.controller.dto.ExtractKeywordsResponse
import net.likelion.dailytales.service.KeywordsExtractService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ai/keywords-extractor")
class KeywordsExtractorController(
    private val keywordsExtractService: KeywordsExtractService,
) {
    @GetMapping
    suspend fun extractKeywords(
        @RequestParam("writing") writing: String,
    ): ExtractKeywordsResponse {
        val keywords = keywordsExtractService.extractKeywordsFrom(writing)

        return ExtractKeywordsResponse.of(keywords)
    }
}