package com.example.servive


import com.example.entity.User
import com.example.repository.UserRepository
import com.example.service.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.*

class UserServiceTest {

    val repo = mock(UserRepository::class.java)
    private val userService: UserService = UserService(repo)

    @BeforeEach
    fun setUp() {
        clearInvocations(repo)
    }

    @Test
    fun findUserByLastNameTest() {
        // Given
        val user = User("foo", "bar", "baz@taz.com", UUID.randomUUID())
        `when`( repo.findByLastName("foo")).thenReturn(listOf(user))

        // When
        val findUserResult = userService.findUserByLastName("foo")

        // Then
        assert(findUserResult.isNotEmpty())
        assert(findUserResult[0].lastName.equals("foo"))
        assert(findUserResult[0].firstName.equals("bar"))
        assert(findUserResult[0].email.equals("baz@taz.com"))
    }

    @Test
    fun findManyUsersByLastNameTest() {
        //Given
        val user1 = User("foo", "bar", "baz@taz.com", UUID.randomUUID())
        val user2 = User("foo", "bar2", "baz2@taz.com", UUID.randomUUID())
        `when`( repo.findByLastName("foo")).thenReturn(listOf(user1, user2))

        // When
        val findUserResult = userService.findUserByLastName("foo")

        // Then
        assert(findUserResult.size == 2)
        assert(findUserResult[0].lastName.equals("foo"))
        assert(findUserResult[0].firstName.equals("bar"))
        assert(findUserResult[0].email.equals("baz@taz.com"))
        assert(findUserResult[1].lastName.equals("foo"))
        assert(findUserResult[1].firstName.equals("bar2"))
        assert(findUserResult[1].email.equals("baz2@taz.com"))
    }

    @Test
    fun findNoUserUsersByLastNameTest() {
        //Given
        `when`( repo.findByLastName("foo")).thenReturn(listOf())

        // When
        val findUserResult = userService.findUserByLastName("foo")

        // Then
        assert(findUserResult.isEmpty())
    }
}