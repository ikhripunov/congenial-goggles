package com.example.service

import com.example.entity.mapper.UserMapper
import com.example.model.UserDTO
import com.example.repository.UserRepository
import jakarta.validation.Valid
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired
    val repo:UserRepository
) {

    val mapper = Mappers.getMapper(UserMapper::class.java)

    fun createUser(@Valid user: UserDTO): UserDTO {
        val saved = repo.save(mapper.dtoToEntity(user))
        return mapper.entityToDto(saved)
    }

    fun findUser(lastName: String): MutableList<UserDTO> {
        val findBySurname = repo.findBySurname(lastName)
        return findBySurname.map { user: com.example.entity.User -> mapper.entityToDto(user) }.toMutableList()
    }
}