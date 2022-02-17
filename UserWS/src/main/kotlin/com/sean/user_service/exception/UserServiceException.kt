package com.sean.user_service.exception

import java.lang.RuntimeException

class UserServiceException(
    private val msg: String
): RuntimeException(msg) {
}