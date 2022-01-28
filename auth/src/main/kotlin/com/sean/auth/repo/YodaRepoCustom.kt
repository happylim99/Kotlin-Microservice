package com.sean.auth.repo

import com.sean.auth.ui.res.TestUser
import com.sean.auth.ui.res.UserRes

interface YodaRepoCustom {
    fun yodaSayGetByDynamicTable(askYoda: HashMap<String, Any>): MutableList<Any?>
    fun yodaSayFindUserByUid(askYoda: HashMap<String, Any>): TestUser?
}