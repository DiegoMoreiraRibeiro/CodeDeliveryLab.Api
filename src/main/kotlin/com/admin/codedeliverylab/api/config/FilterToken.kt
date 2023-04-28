package com.admin.codedeliverylab.api.config

import com.admin.codedeliverylab.api.repository.UsuarioRepository
import com.admin.codedeliverylab.api.services.interfaces.AuthenticateServices
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class FilterToken : OncePerRequestFilter() {
    @Autowired
    private val authenticateService: AuthenticateServices? = null

    @Autowired
    private val usuarioRepository: UsuarioRepository? = null

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse, filterChain: FilterChain,
    ) {
        try {
            val token: String
            val authorizationHeader = request.getHeader("Authorization")
            if (authorizationHeader != null) {
                token = authorizationHeader.replace("Bearer ", "")
                val subject: String = authenticateService?.getSubject(token) ?: throw Exception("Token não encontrado")
                val usuario = usuarioRepository?.findByEmail(subject) ?: throw Exception("Usuario não encontrado")

                val authorities: Collection<GrantedAuthority> =
                        listOf(SimpleGrantedAuthority(usuario.role))

                val authentication = UsernamePasswordAuthenticationToken(
                        usuario.email, usuario.senha, authorities
                )
                SecurityContextHolder.getContext().authentication = authentication
            }
            filterChain.doFilter(request, response)
        } catch (ex: Exception) {
            throw Exception(ex)
        }

    }
}