package com.sean.auth.apicomm

import com.sean.auth.ui.res.UserRes
import com.sean.base.annotation.Slf4j.Companion.log
import feign.FeignException
import org.springframework.web.server.ResponseStatusException

class UserServiceFallback(
    private val cause: Throwable?
): UserServiceClient {

    override fun getUserWSUser(uid: String): UserRes {
//        println("${(cause as ResponseStatusException).status}")
//        if((cause as ResponseStatusException).status == 404) {
//
//        }
//        println("${cause!!::class.simpleName}")
//        if (cause is FeignException && (cause as FeignException).status() == 404) {
//            log.error("getUserWSUser error, error msg = ${(cause as Throwable).localizedMessage}")
//        } else {
//            log.error("unknown error = ${cause?.localizedMessage}")
//        }


        if(cause is ResponseStatusException) {
            val stat = (cause as ResponseStatusException).status.toString().split(" ")[0]
            if(stat == "404") {
                log.error("getUserWSUser error, error msg = ${cause.localizedMessage}")
            }
        } else {
            log.error("Unknown error = ${cause?.localizedMessage}")
        }
        return UserRes(uid = "bb")
    }
}