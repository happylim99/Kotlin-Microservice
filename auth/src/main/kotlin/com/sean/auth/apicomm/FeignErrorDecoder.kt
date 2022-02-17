package com.sean.auth.apicomm

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import java.lang.Exception

class FeignErrorDecoder: ErrorDecoder {

    override fun decode(methodKey: String?, response: Response?): Exception {
        when (response!!.status()) {
            400 -> println("400")
            404 -> {
                if(methodKey!!.contains("getUserWSUser")) {
                    return ResponseStatusException(
                        HttpStatus.valueOf(response.status()),
                        "User not found"
                    )
                }
            }
            else -> {
                return Exception("Feign error else ${response.reason()}")
            }
        }
        return Exception()
    }
}