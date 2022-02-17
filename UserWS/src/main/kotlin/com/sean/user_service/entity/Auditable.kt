package com.sean.user_service.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import java.util.*
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditableListener::class)
abstract class Auditable<T> {

    @Basic
    @CreatedBy
    var insertUser: String? = null

    @Basic
    @LastModifiedBy
    var updateUser: String? = null

    @Basic
    var insertDate: Date? = null

    @Basic
    var updateDate: Date? = null

}