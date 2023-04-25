package com.admin.codedeliverylab.api.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
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

