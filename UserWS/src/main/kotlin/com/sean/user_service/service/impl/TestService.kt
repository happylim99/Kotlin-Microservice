package com.sean.user_service.service.impl

import com.sean.user_service.repo.UserRepo
import org.springframework.stereotype.Service

@Service
class TestService(
    private val repo: UserRepo
) {

//    fun testFindByUid(uid: String): List<TestUser>? {
//        return repo.findUserByUid(uid)
//    }
}