package com.stellar.burgers.client;

import com.stellar.burgers.model.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient {

    private static final String BASE_URI = "https://stellarburgers.education-services.ru";
    private static final String ORDER_PATH = "/api/orders";

    @Step("Создание заказа с ингредиентами: {order.ingredients}")
    public Response createOrder(Order order, String accessToken) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(BASE_URI)
                .body(order)
                .post(ORDER_PATH);
    }

    @Step("Создание заказа без авторизации")
    public Response createOrderWithoutAuth(Order order) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(order)
                .post(ORDER_PATH);
    }
}