package com.example.controller

import com.example.api.UsersApi
import com.example.model.UserDTO
import com.example.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(@Autowired val userService: UserService) : UsersApi {

    override fun createUser(userDTO: UserDTO?): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(userService.createUser(userDTO!!))
    }

    override fun findUserByLastName(lastName: String): ResponseEntity<List<UserDTO>> {
        return ResponseEntity.ok(userService.findUser(lastName))
    }
}