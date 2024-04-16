package com.example.repository

import com.example.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserRepository:CrudRepository<User, UUID> {
    fun findBySurname(surname:String):List<User>
}