package com.sean.user_service.service

import com.sean.user_service.ui.req.AddrCreateReq
import com.sean.user_service.ui.req.AddrUptReq
import com.sean.user_service.ui.res.AddrRes

interface AddressService {

    fun createOne(addrReq: AddrCreateReq): AddrRes
    fun updateOne(userReq: AddrUptReq): AddrRes
    fun showOne(id: String): AddrRes?
    fun showAll(): List<AddrRes>

}