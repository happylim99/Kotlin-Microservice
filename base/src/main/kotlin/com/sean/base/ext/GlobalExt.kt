package com.sean.base.ext

import java.util.*
import kotlin.reflect.full.memberProperties

fun getUUID() = UUID.randomUUID().toString().replace("-", "")

inline fun <reified T : Any> T.asMap() : Map<String, Any?> {
    val props = T::class.memberProperties.associateBy { it.name }
    return props.keys.associateWith { props[it]?.get(this) }
}

class GlobalExt {
}