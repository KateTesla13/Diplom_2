package com.stellar.burgers.client;

import com.stellar.burgers.model.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String BASE_URI = "https://stellarburgers.education-services.ru";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String USER_PATH = "/api/auth/user";

    @Step("Создание пользователя с email: {user.email}")
    public Response createUser(User user) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(user)
                .post(REGISTER_PATH);
    }

    @Step("Авторизация пользователя с email: {user.email}")
    public Response loginUser(User user) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(user)
                .post(LOGIN_PATH);
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(BASE_URI)
                .delete(USER_PATH);
    }

    public String getAccessToken(Response response) {
        return response.jsonPath().getString("accessToken");
    }

    public String getRefreshToken(Response response) {
        return response.jsonPath().getString("refreshToken");
    }
}