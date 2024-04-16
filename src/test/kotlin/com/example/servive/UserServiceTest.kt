package com.example.servive


import com.example.entity.User
import com.example.repository.UserRepository
import com.example.service.UserService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.*

class UserServiceTest {

    val repo = mock(UserRepository::class.java)
    private val userService: UserService = UserService(repo)

    @Test
    fun basicTest() {
        val user = User("foo", "bar", "baz@taz.com", UUID.randomUUID())
        `when`( repo.findBySurname("foo")).thenReturn(listOf(user).toList())
        val findUser = userService.findUser("foo")
        assert(findUser.isNotEmpty())
        assert(findUser[0].lastName.equals("foo"))
        assert(findUser[0].firstName.equals("bar"))
        assert(findUser[0].email.equals("baz@taz.com"))
    }
}