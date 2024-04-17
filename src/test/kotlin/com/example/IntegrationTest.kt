package com.example

import com.example.entity.User
import com.example.model.UserDTO
import com.example.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTest(
    @Autowired
    val mockMvc: MockMvc,
    @Autowired
    val repo: UserRepository
) {

    val om = ObjectMapper()

    @BeforeEach
    fun setUp() {
        repo.deleteAll()
    }

    @ParameterizedTest
    @MethodSource("createUserInputsAndResults")
    fun createUserTest(userDTO: UserDTO?, status: Int) {
        // Given the userDTO
        // When
        mockMvc.post("/v1/users") {
            contentType = MediaType.APPLICATION_JSON
            content = om.writeValueAsString(userDTO)
        }
        // Then
            .andExpect { status { isEqualTo(status) } }
    }

    @ParameterizedTest
    @MethodSource("findUserByLastNameInputsAndResults")
    fun findUserByLastNameTest(usersInDb: List<User>, lastName: String, numberFound: Int, status: Int,) {
        // Given
        repo.saveAll(usersInDb)

        // When
        mockMvc.get("/v1/users?lastName=$lastName")
            .andExpect { status { isEqualTo(status) } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { jsonPath("$.length()", Matchers.equalTo(numberFound)) }
    }

    companion object {
        @JvmStatic
        fun createUserInputsAndResults(): List<Arguments> {
            return listOf(
                Arguments.of(UserDTO("bar").firstName("one").email("buzz"), 204),
                Arguments.of(UserDTO().firstName("two").email("buzz"), 400),
                Arguments.of(UserDTO("").firstName("three").email("buzz"), 400),
                Arguments.of(null, 400)
            )
        }

        @JvmStatic
        fun findUserByLastNameInputsAndResults(): List<Arguments> {
            return listOf(
                Arguments.of(
                    listOf(User("foo", "bar", "buzz"), User("foo", "bar", "buzz")),
                    "foo",
                    2,
                    200
                ),
                Arguments.of(
                    listOf(User("foo", "bar", "buzz"), User("bar", "bar", "buzz")),
                    "foo",
                    1,
                    200
                ),
                Arguments.of(
                    listOf(User("buzz", "bar", "buzz"), User("bar", "bar", "buzz")),
                    "foo",
                    0,
                    200
                ),
                Arguments.of(
                    listOf(User("buzz", "bar", "buzz"), User("bar", "bar", "buzz")),
                    "",
                    1,
                    400
                ),
            )
        }
    }
}