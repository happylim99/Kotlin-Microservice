package com.sean.auth.repo

import com.sean.auth.ui.res.TestUser
import com.sean.auth.ui.res.UserRes
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class YodaRepoCustomImpl: YodaRepoCustom {

    @PersistenceContext
    private lateinit var em: EntityManager

    override fun yodaSayGetByDynamicTable(askYoda: HashMap<String, Any>): MutableList<Any?> {
        var sb = StringBuilder()
        sb.append("select ")
        sb.append("* from user u")


        if(!askYoda["uid"].toString().isNullOrBlank()) {
            sb.append(" where u.uid = :uid")
        }

        val query = em.createNativeQuery(sb.toString())

        if(!askYoda["uid"].toString().isNullOrBlank()) {
            query.setParameter("uid", askYoda["uid"])
        }
        return query.resultList
    }

    override fun yodaSayFindUserByUid(askYoda: HashMap<String, Any>): TestUser? {
        var sb = StringBuilder()
        sb.append("select ")
        sb.append("u.uid, u.name, u.username, u.email from user u")

        if (!askYoda["uid"].toString().isNullOrBlank()) {
            sb.append(" where u.uid = :uid")
        }

        val query = em.createNativeQuery(sb.toString())

        if (!askYoda["uid"].toString().isNullOrBlank()) {
            query.setParameter("uid", askYoda["uid"])
        }
        val res = query.singleResult
        val aa = res as Array<out Any>
        println(aa[0])
        return TestUser(
            aa[0] as String,
            aa[1] as String
        ).apply {
            linkToUserControllerShowOne(this)
        }

//        val resultList = query.resultList
//        val result = resultList.map {
//                it ->
//            val lst = it as Array<out Any>
//            UserRes(
//                lst[0] as String,
//                lst[1] as String,
//                lst[2] as String,
//                lst[3] as String,
//            )
//        }
//        return result
    }
}