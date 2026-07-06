package com.stellar.burgers.test;

import com.stellar.burgers.client.UserClient;
import com.stellar.burgers.model.DataGenerator;
import com.stellar.burgers.model.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

public class BaseTest {

    protected UserClient userClient;
    protected User user;
    protected String accessToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";
        userClient = new UserClient();
        user = DataGenerator.generateUser();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    protected void loginUser() {
        Response loginResponse = userClient.loginUser(user);
        accessToken = userClient.getAccessToken(loginResponse);
    }
}