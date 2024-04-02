package com.example.entity.mapper

import com.example.model.User
import org.mapstruct.Mapper

@Mapper
interface UserMapper {
    fun dtoToEntity(dto:User):com.example.entity.User
    fun entityToDto(entity:com.example.entity.User):User
}