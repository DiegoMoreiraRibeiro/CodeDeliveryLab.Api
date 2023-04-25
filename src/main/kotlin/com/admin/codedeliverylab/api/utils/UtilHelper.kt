package com.admin.codedeliverylab.api.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.security.MessageDigest
import kotlin.reflect.KClass



inline fun <reified T : Any, reified R : Any> mapObject(source: T, destinationClass: KClass<R>): R {
    return try {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(KotlinModule())
        objectMapper.convertValue(source, destinationClass.java)
    } catch (ex: Exception) {
        throw ex
    }
}

inline fun <reified T : Any, reified R : Any> mapList(list: List<T>, clazz: KClass<R>): List<R> {
    val mapper = ObjectMapper().registerModule(KotlinModule())
    val typeReference = object : TypeReference<List<R>>() {}
    return mapper.convertValue(list, typeReference)
}

fun hashMD5(senha: String): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(senha.toByteArray())
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}
