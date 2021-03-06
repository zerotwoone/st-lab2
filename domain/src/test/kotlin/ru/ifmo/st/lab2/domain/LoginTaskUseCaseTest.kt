package ru.ifmo.st.lab2.domain

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.ifmo.st.lab2.gateway.ServerGateway
import ru.ifmo.st.lab2.gateway.TaskDBGateway
import ru.ifmo.st.lab2.gateway.UserCredentialsGatewy
import kotlin.test.assertFailsWith
import ru.ifmo.st.lab2.sample.*

class LoginTaskUseCaseTest {
    private lateinit var serverGateway: ServerGateway
    private lateinit var login: LoginUseCase

    private lateinit var userCredentialsGatewy: UserCredentialsGatewy

    private val username = "login"
    private val password = "password"

    @BeforeEach
    fun init() {
        serverGateway = mock {
            onBlocking { this.login(username, password) } doReturn true
        }
        userCredentialsGatewy = mock {}
        login = LoginUseCaseImpl(serverGateway, userCredentialsGatewy)
    }

    @Test
    fun `when invoke UseCase`() {
        login(username, password)
        runBlocking { verify(serverGateway).login(username, password) }
        verify(userCredentialsGatewy).store(any())
    }

    @Test
    fun `when invoke UseCase with invalid username`() {
        assertFailsWith<IllegalStateException> {
            login("123", password)
        }
    }

    @Test
    fun `when invoke UseCase with invalid password`() {
        assertFailsWith<IllegalStateException> {
            login(username, "123")
        }
    }

    @Test
    fun `when invoke UseCase with invalid username and password`() {
        assertFailsWith<IllegalStateException> {
            login("123", "123")
        }
    }
}
