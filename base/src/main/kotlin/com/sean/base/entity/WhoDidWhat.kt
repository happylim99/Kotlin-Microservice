package com.sean.base.entity

import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Entity
class WhoDidWhat {

    constructor()

    constructor(who: String, what: String) {
        this.who = who
        this.what = what
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable=false, length=100)
    var who = ""

    @Column(nullable=false, length=150)
    var what = ""

//    @CreationTimestamp
//    lateinit var accessDate: Date

    @Column(columnDefinition = "timestamp")
    var accessDate: Timestamp = Timestamp(System.currentTimeMillis())
}