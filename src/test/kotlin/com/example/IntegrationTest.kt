package com.example

import com.example.entity.User
import com.example.repository.UserRepository
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTest(
    @Autowired
    val mockMvc: MockMvc,
    @Autowired
    val repo: UserRepository
) {

    @Test
    fun testSuccess() {
        repo.save(User("foo", "bar", "baz@taz.com"))

        mockMvc.get("/user?lastName=foo")
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { jsonPath("[0].surname", Matchers.equalTo("foo")) }
    }

    @Test
    fun test400Failure() {
        repo.save(User("foo", "bar", "baz@taz.com"))

        mockMvc.get("/user?lastName=")
            .andExpect { status { isBadRequest() } }
    }
}