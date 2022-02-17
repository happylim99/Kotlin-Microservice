package com.sean.auth.apicomm

import com.sean.auth.ui.res.UserRes
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

class UserServiceFallbackFactory: FallbackFactory<UserServiceClient> {

    override fun create(cause: Throwable?): UserServiceClient {
        return UserServiceFallback(cause)
    }


}