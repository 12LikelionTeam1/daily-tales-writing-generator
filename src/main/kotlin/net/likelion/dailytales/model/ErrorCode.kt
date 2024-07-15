package net.likelion.dailytales.model

enum class ErrorCode(
    val code: String,
    val message: String
) {
    API_ERROR("A0001", "API 요청 중 오류가 발생했습니다.");
}