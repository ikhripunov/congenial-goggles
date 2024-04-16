package com.example.entity.mapper

import com.example.model.UserDTO
import org.mapstruct.Mapper

@Mapper
interface UserMapper {
    fun dtoToEntity(dto:UserDTO):com.example.entity.User
    fun entityToDto(entity:com.example.entity.User):UserDTO
}