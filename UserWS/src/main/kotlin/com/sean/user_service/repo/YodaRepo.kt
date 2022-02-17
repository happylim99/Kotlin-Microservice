package com.sean.user_service.repo

import com.sean.user_service.entity.Yoda
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional
interface YodaRepo: JpaRepository<Yoda, Long>, YodaRepoCustom {
}