package com.stellar.burgers.test;

import com.stellar.burgers.model.DataGenerator;
import com.stellar.burgers.model.User;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {

    private final User loginUser;
    private final int expectedStatusCode;
    private final String expectedMessage;

    public LoginTest(User loginUser, int expectedStatusCode, String expectedMessage) {
        this.loginUser = loginUser;
        this.expectedStatusCode = expectedStatusCode;
        this.expectedMessage = expectedMessage;
    }

    @Parameterized.Parameters(name = "{index}: статус {1}")
    public static Object[][] getLoginData() {
        User validUser = DataGenerator.generateUser();

        return new Object[][]{
                {validUser, SC_OK, null},
                {new User("wrong@yandex.ru", "wrongpass"), SC_UNAUTHORIZED, "email or password are incorrect"}
        };
    }

    @Before
    public void setUpLogin() {
        if (expectedStatusCode == SC_OK) {
            user = loginUser;
            userClient.createUser(user);
        }
    }

    @Test
    public void loginUserTest() {
        Response response = userClient.loginUser(loginUser);

        response.then()
                .log().all()
                .statusCode(expectedStatusCode);

        if (expectedStatusCode == SC_OK) {
            response.then()
                    .body("success", equalTo(true))
                    .body("user.email", equalTo(loginUser.getEmail()))
                    .body("accessToken", notNullValue());
            accessToken = userClient.getAccessToken(response);
        } else {
            response.then()
                    .body("success", equalTo(false))
                    .body("message", equalTo(expectedMessage));
        }
    }
}
