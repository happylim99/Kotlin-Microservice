package com.sean.auth.apicomm

import com.sean.auth.ui.res.UserRes

class UserServiceClientImpl: UserServiceClient {

    override fun getUserWSUser(uid: String): UserRes {
        return UserRes(uid = "bb")
    }
}