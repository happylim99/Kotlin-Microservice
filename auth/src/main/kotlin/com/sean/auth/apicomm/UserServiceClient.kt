package com.sean.auth.apicomm

import com.sean.auth.ui.res.UserRes
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

// pure fall back
//@FeignClient(name = "user-ws", fallback = UserServiceFallback::class)

// fallback with factory
@FeignClient(name = "user-ws", fallbackFactory = UserServiceFallbackFactory::class)
interface UserServiceClient {

    @GetMapping("/ll/user/{uid}")
    fun getUserWSUser(@PathVariable uid: String): UserRes

}