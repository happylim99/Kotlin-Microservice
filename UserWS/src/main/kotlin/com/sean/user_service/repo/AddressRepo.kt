package com.sean.user_service.repo

import com.sean.user_service.entity.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepo: JpaRepository<Address, Long> {

    fun findByAddressId(addressId: String): Address?
}