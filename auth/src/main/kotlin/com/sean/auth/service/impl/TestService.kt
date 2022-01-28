package com.sean.auth.service.impl

import com.sean.auth.repo.UserRepo
import com.sean.auth.ui.res.TestUser
import org.springframework.stereotype.Service

@Service
class TestService(
    private val repo: UserRepo
) {

//    fun testFindByUid(uid: String): List<TestUser>? {
//        return repo.findUserByUid(uid)
//    }
}