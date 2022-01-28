package com.sean.base.repo

import com.sean.base.entity.WhoDidWhat
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional
interface WdwRepo: JpaRepository<WhoDidWhat, Long> {
}