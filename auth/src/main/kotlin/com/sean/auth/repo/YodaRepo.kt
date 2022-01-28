package com.sean.auth.repo

import com.sean.auth.entity.Yoda
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional
interface YodaRepo: JpaRepository<Yoda, Long>, YodaRepoCustom {
}