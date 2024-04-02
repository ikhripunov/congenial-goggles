package com.example.entity

import jakarta.persistence.*
import java.util.*

@Entity
class User (
    @Column
    val surname: String,
    @Column
    val firstName: String?,
    @Column
    val emailAddress: String?,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?=null
)