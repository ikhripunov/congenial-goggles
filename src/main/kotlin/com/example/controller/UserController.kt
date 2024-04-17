package com.example.controller

import com.example.api.UsersApi
import com.example.entity.User
import com.example.entity.mapper.UserMapper
import com.example.model.ErrorResponse
import com.example.model.UserDTO
import com.example.service.UserService
import jakarta.validation.ConstraintViolationException
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(@Autowired val userService: UserService) : UsersApi {

    val mapper = Mappers.getMapper(UserMapper::class.java)
    override fun createUser(userDTO: UserDTO?): ResponseEntity<Void> {
        val user = mapper.dtoToEntity(userDTO!!)
        userService.createUser(user)
        return ResponseEntity.noContent().build()
    }

    override fun findUserByLastName(lastName: String): ResponseEntity<List<UserDTO>> {
        return ResponseEntity.ok(userService.findUserByLastName(lastName).map { user: User -> mapper.entityToDto(user) })
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(ErrorResponse().message("Last Name must not be null"))
    }

    @ExceptionHandler(NullPointerException::class)
    fun handleNullPointerException(): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(ErrorResponse().message("User must not be null"))
    }
}