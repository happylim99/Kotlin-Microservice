package com.sean.auth.controller

import com.sean.auth.repo.UserDto2
import com.sean.auth.repo.YodaRepo
import com.sean.auth.service.UserService
import com.sean.auth.service.impl.TestService
import com.sean.auth.service.impl.UserServiceImpl
import com.sean.auth.ui.req.UserCrtReq
import com.sean.auth.ui.req.UserUptReq
import com.sean.auth.ui.res.TestUser
import com.sean.auth.ui.res.UserRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RestController
@CrossOrigin("*")
@RequestMapping("/user")
class UserController @Autowired constructor(
    private val srv: UserService,
    private val yodaRepo: YodaRepo,
    private val srvImpl: UserServiceImpl,
    private val testSrv: TestService
) {

    @PostMapping("/register")
    fun createOne(@RequestBody userReq: UserCrtReq): ResponseEntity<UserRes> {
        return ResponseEntity.ok(srv.createOne(userReq))
    }

    @PutMapping("/{uid}")
    fun updateOne(@PathVariable uid: String,
                  @RequestBody userReq: UserUptReq): ResponseEntity<UserRes> {
        return ResponseEntity.ok(srv.updateOne(uid, userReq))
    }

    @GetMapping("/{uid}")
    fun showOne(@PathVariable uid: String) = ResponseEntity.ok(srv.showOne(uid))

    @GetMapping("test/{uid}")
    fun showOne2(@PathVariable uid: String) = ResponseEntity.ok(srvImpl.showOne2(uid))

    @GetMapping("/showAllValid")
    fun showAllValid() = ResponseEntity.ok(srv.showAllValid())

    @GetMapping("/showAllVoid")
    fun showAllVoid() = ResponseEntity.ok(srv.showAllVoid())

    @GetMapping("/showAll")
    fun showAll() = ResponseEntity.ok(srv.showAll())

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: String) = ResponseEntity.ok(srv.deleteOne(id))

    @GetMapping("/showPag")
    fun showPag(@RequestParam(value = "page", defaultValue = "1") page: Int,
                @RequestParam(value = "limit", defaultValue = "5") limit:Int
    ): ResponseEntity<List<UserRes>> {
        return ResponseEntity.ok(srv.showPag(page, limit))
    }

    @GetMapping("/refreshToken")
    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse) {
        srv.refreshToken(request, response)
    }

    @GetMapping("/testYoda/{uid}")
    fun testYoda(@PathVariable uid: String): ResponseEntity<MutableList<Any?>> {
        val askYoda = HashMap<String, Any>()
        askYoda["uid"] = uid
        return ResponseEntity.ok(yodaRepo.yodaSayGetByDynamicTable(askYoda))
    }

    @GetMapping("/testYoda2/{uid}")
    fun testYoda2(@PathVariable uid: String): ResponseEntity<TestUser?> {
        val askYoda = HashMap<String, Any>()
        askYoda["uid"] = uid
        return ResponseEntity.ok(yodaRepo.yodaSayFindUserByUid(askYoda))
    }

    @GetMapping("/user-service/{uid}")
    fun getUserServiceUser(@PathVariable uid: String) = ResponseEntity.ok(srv.getUserServiceUser(uid))

//    @GetMapping("/testUser/{uid}")
//    fun testUser(@PathVariable uid: String): ResponseEntity<List<TestUser>?> {
//        return ResponseEntity.ok(testSrv.testFindByUid(uid))
//    }
}