package apiTests

import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.equalTo
import org.json.JSONObject
import org.testng.annotations.Test
import java.util.UUID

class CrocodileFarmTest {

    @Test(groups = ["crocodileFarmUserNotRegistered"])
    fun crocodileFarmNotRegistered() {
        Given {
            baseUri(BASE_URL)
        } When {
            get("/my/crocodiles/")
        } Then {
            statusCode(401)
            statusLine("HTTP/1.1 401 Unauthorized")
            body("detail", equalTo("Authentication credentials were not provided."))
        }
    }

    @Test(groups = ["crocodileFarmUserRegistered"])
    fun crocodileRegistered() {
        val registerUser = JSONObject()
        registerUser.put("username", USERNAME)
        registerUser.put("first_name", FIRST_NAME)
        registerUser.put("last_name", LAST_NAME)
        registerUser.put("email", EMAIL)
        registerUser.put("password", PASSWORD)

        Given {
            baseUri(BASE_URL)
            contentType(ContentType.JSON)
            body(registerUser.toString())
        } When {
            post("/user/register/")
        } Then {
            statusCode(201)
            body("username", equalTo(USERNAME))
        }

        val userLogin = JSONObject()
        userLogin.put("username", USERNAME)
        userLogin.put("password", PASSWORD)

        val response = Given {
            auth()
            baseUri(BASE_URL)
            contentType(ContentType.JSON)
            body(userLogin.toString())
        } When {
            post("/auth/token/login/")
        } Then {
            statusCode(200)
        } Extract {
            response()
        }
        val authToken = response.jsonPath().getJsonObject<String>("access")

        val list = listOf("M", "F")
        val crocodile = JSONObject()
        crocodile.put("name", CROCODILE_NAME)
        crocodile.put("sex", list.random())
        crocodile.put("date_of_birth", "2019-01-01")

        Given {
            header("Authorization", "Bearer $authToken")
            baseUri(BASE_URL)
            contentType(ContentType.JSON)
            body(crocodile.toString())
        } When {
            post("/my/crocodiles/")
        } Then {
            statusCode(201)
        }

        val crocodileCreated = Given {
            header("Authorization", "Bearer $authToken")
            baseUri(BASE_URL)
        } When {
            get("/my/crocodiles/")
        } Then {
            statusCode(200)
            body("name", contains(CROCODILE_NAME))
        } Extract {
            response()
        }

        val crocodileId = crocodileCreated.jsonPath().getJsonObject<ArrayList<Int>>("id").first()
        crocodile.put("name", CROCODILE_NAME_UPDATED)

        Given {
            header("Authorization", "Bearer $authToken")
            baseUri(BASE_URL)
            contentType(ContentType.JSON)
            body(crocodile.toString())
        } When {
            patch("/my/crocodiles/$crocodileId/")
        } Then {
            statusCode(200)
        }

        Given {
            header("Authorization", "Bearer $authToken")
            baseUri(BASE_URL)
        } When {
            get("/my/crocodiles/$crocodileId/")
        } Then {
            statusCode(200)
            body("name", equalTo(CROCODILE_NAME_UPDATED))
        }
    }

    private companion object {
        val USERNAME = "user${UUID.randomUUID().toString().substring(0, 4)}"
        val FIRST_NAME = "First${UUID.randomUUID().toString().substring(0, 4)}"
        val LAST_NAME = "Last${UUID.randomUUID().toString().substring(0, 4)}"
        val EMAIL = "test${UUID.randomUUID().toString().substring(0, 4)}@test.com"
        const val CROCODILE_NAME = "Crocodile Name"
        const val CROCODILE_NAME_UPDATED = "Updated"
        const val PASSWORD = "aA1111111#"
        const val BASE_URL = "https://test-api.k6.io"
    }
}
