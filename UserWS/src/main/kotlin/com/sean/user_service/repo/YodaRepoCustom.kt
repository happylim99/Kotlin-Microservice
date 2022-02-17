package com.sean.user_service.repo

import com.sean.user_service.ui.res.TestUser

interface YodaRepoCustom {
    fun yodaSayGetByDynamicTable(askYoda: HashMap<String, Any>): MutableList<Any?>
    fun yodaSayFindUserByUid(askYoda: HashMap<String, Any>): TestUser?
}