package net.likelion.dailytales.controller

import net.likelion.dailytales.controller.dto.GenerateWritingResponse
import net.likelion.dailytales.service.WritingGenerateService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ai/writing-generator")
class WritingGeneratorController(
    private val writingGenerateService: WritingGenerateService
) {
    @GetMapping
    suspend fun generateWriting(
        @RequestParam("prompt") prompt: String,
    ): GenerateWritingResponse {
        val writing = writingGenerateService.generateWriting(prompt = prompt)

        return GenerateWritingResponse(result = writing.content)
    }
}