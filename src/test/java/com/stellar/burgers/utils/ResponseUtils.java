package com.stellar.burgers.utils;

import io.restassured.response.Response;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

public class ResponseUtils {

    public static void assertOrderSuccessResponse(Response response) {
        response.then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("order", notNullValue());
    }

    public static void assertOrderErrorResponse(Response response, int statusCode, String message) {
        response.then()
                .statusCode(statusCode)
                .body("success", equalTo(false))
                .body("message", equalTo(message));
    }

    public static void assertSuccessResponse(Response response, String email, String name) {
        response.then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo(email))
                .body("user.name", equalTo(name))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    public static void assertErrorResponse(Response response, int statusCode, String message) {
        response.then()
                .statusCode(statusCode)
                .body("success", equalTo(false))
                .body("message", equalTo(message));
    }

    public static void assertLoginSuccessResponse(Response response, String email) {
        response.then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo(email))
                .body("accessToken", notNullValue());
    }
    public static void assertInternalServerError(Response response) {
        response.then()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}