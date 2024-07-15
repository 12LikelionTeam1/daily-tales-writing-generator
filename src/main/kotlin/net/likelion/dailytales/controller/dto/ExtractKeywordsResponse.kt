package net.likelion.dailytales.controller.dto

import net.likelion.dailytales.service.Keywords

data class ExtractKeywordsResponse(
    val keywords: List<String>
) {
    companion object {
        fun of(keywords: Keywords): ExtractKeywordsResponse {
            return ExtractKeywordsResponse(keywords = keywords.keywords)
        }
    }
}