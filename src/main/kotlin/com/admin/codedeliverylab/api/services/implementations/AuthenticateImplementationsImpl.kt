package com.admin.codedeliverylab.api.services.implementations

import com.admin.codedeliverylab.api.services.interfaces.AuthenticateServices
import com.admin.codedeliverylab.api.services.interfaces.UsuarioService
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class AuthenticateImplementationsImpl(
    private val usuarioService: UsuarioService
) : AuthenticateServices {

    @Value("\${key-issuer}")
    lateinit var keyIssuer: String

    @Value("\${key-secret}")
    lateinit var keySecret: String


    override fun gerarToken(usuario: UsernamePasswordAuthenticationToken): String {
        val dataCriacao = LocalDateTime.now().toInstant(ZoneOffset.UTC)
        val dataExpiracao = dataCriacao.plus(1, ChronoUnit.DAYS)
        val algorithm = Algorithm.HMAC256(keySecret)

        return JWT.create()
            .withIssuer(keyIssuer)
            .withIssuedAt(Date.from(dataCriacao))
            .withExpiresAt(Date.from(dataExpiracao))
            .withSubject(usuario.name.toString())
            .withClaim("name", usuario.name)
            .sign(algorithm)

    }

    override fun getSubject(token: String?): String {
        try {
            return JWT.require(Algorithm.HMAC256(keySecret))
                .withIssuer(keyIssuer)
                .build().verify(token).subject
        }catch (ex: Exception){
            throw Exception(ex)
        }

    }

}