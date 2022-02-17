package com.sean.user_service.service.impl

/*
internal class UserServiceImplTest {

    val userId = "abcde12345"
    val encryptedPassword = "abcde12345"

    @RelaxedMockK
    private lateinit var userRepo: UserRepo
    @MockK
    private lateinit var roleRepo: RoleRepo
    @MockK
    private lateinit var bcrypt: BCryptPasswordEncoder
    @MockK
    private lateinit var mapper: ObjectMapper
    @MockK
    private lateinit var authUtil: AuthUtil

    @InjectMockKs
    private lateinit var usrSrv: UserServiceImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun createOne() {
        every { bcrypt.encode(any()) } returns encryptedPassword
        every { userRepo.save(any()) } returns getUser()
        val userRes = usrSrv.createOne(getUserCreateReq())
        assertEquals("1a", userRes?.uid)
    }

    @Test
    fun showAll() {
//        fail("implementing")
    }

    @Test
    fun showOne() {
        every { userRepo.findByUserId(any()) } returns getUser()
        val userRes = usrSrv.showOne("1a")
        assertEquals("1a", userRes?.userId)
        assertTrue(true)
    }

    @Test
    fun `showOne throw exception`() {
        every { userRepo.findByUserId(any()) } returns null
        org.junit.jupiter.api.assertThrows<Exception> { usrSrv.showOne("1a") }
    }

    private fun getUser(): User {
        val user = User(1L, "1a", "aaa",
            "aaa", "aaa@aaa.com", "token",
            false, "abcd", mutableSetOf())
        return user
    }

    private fun getUserCreateReq(): UserCrtReq {
        val userReq = UserCrtReq("aaa", "aaa",
            "aaa@aaa.com", encryptedPassword)
        return userReq
    }
}
 */