package net.likelion.dailytales.config

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class JwtTokenVerifier(
    @Value("\${token.secret}") private val secret: String,
) {
    private val signKey: SecretKey = SecretKeySpec(secret.toByteArray(), "HmacSHA256")

fun verify(token: String) {
    val userId = Jwts.parser()
        .verifyWith(signKey)
        .build()
        .parseSignedClaims(token)
        .payload["userId"]
}
}
