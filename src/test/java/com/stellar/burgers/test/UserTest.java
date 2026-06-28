package com.stellar.burgers.test;

import com.stellar.burgers.model.DataGenerator;
import com.stellar.burgers.model.User;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class UserTest extends BaseTest {

    private final User testUser;
    private final int expectedStatusCode;
    private final String expectedMessage;

    public UserTest(User testUser, int expectedStatusCode, String expectedMessage) {
        this.testUser = testUser;
        this.expectedStatusCode = expectedStatusCode;
        this.expectedMessage = expectedMessage;
    }

    @Parameterized.Parameters(name = "Создание пользователя: статус {1}, сообщение \"{2}\"")
    public static Object[][] getTestData() {
        return new Object[][]{
                {DataGenerator.generateUser(), SC_OK, null},
                {DataGenerator.generateUserWithoutEmail(), SC_FORBIDDEN, "Email, password and name are required fields"},
                {DataGenerator.generateUserWithoutPassword(), SC_FORBIDDEN, "Email, password and name are required fields"},
                {DataGenerator.generateUserWithoutName(), SC_FORBIDDEN, "Email, password and name are required fields"}
        };
    }

    @Test
    public void createUserTest() {
        Response response = userClient.createUser(testUser);

        response.then()
                .log().all()
                .statusCode(expectedStatusCode);

        if (expectedStatusCode == SC_OK) {
            response.then()
                    .body("success", equalTo(true))
                    .body("user.email", equalTo(testUser.getEmail()))
                    .body("user.name", equalTo(testUser.getName()))
                    .body("accessToken", notNullValue())
                    .body("refreshToken", notNullValue());
        } else {
            response.then()
                    .body("success", equalTo(false))
                    .body("message", equalTo(expectedMessage));
        }
    }
}
