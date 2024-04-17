package com.example.service

import com.example.entity.User
import com.example.repository.UserRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired
    val repo:UserRepository
) {
    fun createUser(@Valid user: User) {
        repo.save(user)
    }

    fun findUserByLastName(lastName: String): List<User> {
      return repo.findByLastName(lastName)
    }
}