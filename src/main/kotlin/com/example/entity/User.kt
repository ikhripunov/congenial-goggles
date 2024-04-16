package com.example.entity

import jakarta.persistence.*
import java.util.*

@Entity
class User (
    @Column
    val lastName: String,
    @Column
    val firstName: String?,
    @Column
    val email: String?,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?=null
)