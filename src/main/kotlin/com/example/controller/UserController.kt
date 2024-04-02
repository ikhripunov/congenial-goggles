package com.example.controller

import com.example.api.UserApi
import com.example.model.User
import com.example.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(@Autowired val userService: UserService) : UserApi {

    override fun createUser(user: User?): ResponseEntity<User> {
        return ResponseEntity.ok(userService.createUser(user!!))
    }

    override fun findUser(lastName: String?): ResponseEntity<MutableList<User>> {
        return ResponseEntity.ok(userService.findUser(lastName!!))
    }
}