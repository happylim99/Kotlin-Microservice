package com.sean.user_service.repo

import com.sean.user_service.entity.Role
import com.sean.user_service.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

@Transactional
interface UserRepo: JpaRepository<User, Long> {

    companion object{
        const val findByUidQ = "select a.id, a.uid from User a where a.uid = :uid"
    }

    fun findByEmail(email: String): User?

    fun findByUid(uid: String): User?

    @Query(value = "select a.id, a.uid, a.email from user a where a.uid = :uid", nativeQuery = true)
    fun findByUid2(@Param("uid") uid: String): Array<Any>?

    @Query(value = "select a.uid, a.name, a.username, a.email from user a where a.uid = :uid", nativeQuery = true)
    fun findByUid3(@Param("uid") uid: String): UserDto2?

    fun deleteByUid(uid: String): Long

//    @Query("select a.id, a.uid, a.email from user a where a.uid = :uid")
    fun <T> findByUid(uid: String?, type: Class<T>?): Collection<T>?

//    @Query(value = "select com.sean.auth.ui.res.TestUser(a.uid as uid, a.name as name) from user a where a.uid = :uid")
//    fun findUserByUid(uid: String): List<TestUser>?

}

interface UserDto2 {
    val uid: String?
    val name: String?
    val username: String?
    val email: String?
    val roles: MutableList<Role>?
}
