package com.sean.user_service.ui.res

import com.sean.user_service.controller.UserController
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.linkTo

class TestUser(): RepresentationModel<TestUser>() {

    constructor(uid: String, name: String): this() {
        this.uid = uid
        this.name = name
    }

    var uid: String = ""
    var name: String = ""

    fun linkToUserControllerShowOne(testUser: TestUser): TestUser {
        return testUser.add(linkTo<UserController> {
            this.showOne(testUser.uid)
        }.withRel("roles"))
    }
}