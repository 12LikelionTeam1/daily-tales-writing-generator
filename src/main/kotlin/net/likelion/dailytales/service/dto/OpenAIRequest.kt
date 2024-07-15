package net.likelion.dailytales.service.dto

data class OpenAIRequest(
    val model: String,
    val messages: List<Message>
) {
    companion object {
        fun of(model: String, prompt: String): OpenAIRequest {
            return OpenAIRequest(
                model = model,
                messages = listOf(Message("user", prompt))
            )
        }
    }
}
