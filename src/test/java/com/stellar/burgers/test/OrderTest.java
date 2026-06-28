package com.stellar.burgers.test;

import com.stellar.burgers.client.OrderClient;
import com.stellar.burgers.model.DataGenerator;
import com.stellar.burgers.model.Order;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.apache.http.HttpStatus.*;
import static com.stellar.burgers.utils.ResponseUtils.*;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final Order order;
    private final boolean withAuth;
    private final int expectedStatusCode;
    private final String expectedMessage;
    private final boolean isSuccess;
    private OrderClient orderClient;

    public OrderTest(Order order, boolean withAuth, int expectedStatusCode, String expectedMessage, boolean isSuccess) {
        this.order = order;
        this.withAuth = withAuth;
        this.expectedStatusCode = expectedStatusCode;
        this.expectedMessage = expectedMessage;
        this.isSuccess = isSuccess;
    }

    @Parameterized.Parameters(name = "{index}: авторизация={1}, статус {2}")
    public static Object[][] getOrderData() {
        List<String> validIngredients = Arrays.asList("61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f");
        List<String> emptyIngredients = Arrays.asList();
        List<String> invalidIngredients = Arrays.asList("invalid_hash_123");

        return new Object[][]{
                {new Order(validIngredients), true, SC_OK, null, true},
                {new Order(validIngredients), false, SC_OK, null, true},
                {new Order(emptyIngredients), true, SC_BAD_REQUEST, "Ingredient ids must be provided", false},
                {new Order(invalidIngredients), true, SC_INTERNAL_SERVER_ERROR, null, false}
        };
    }

    @Before
    public void setUp() {
        super.setUp();
        orderClient = new OrderClient();
        if (withAuth) {
            System.out.println("🔍 Генерируем пользователя...");
            user = DataGenerator.generateUser();
            System.out.println("✅ Пользователь: " + user.getEmail());
            userClient.createUser(user);
            loginUser();
        }
    }

    @Test
    public void createOrderTest() {
        Response response = withAuth
                ? orderClient.createOrder(order, accessToken)
                : orderClient.createOrderWithoutAuth(order);

        response.then().log().all();

        if (isSuccess) {
            assertOrderSuccessResponse(response);
        } else if (expectedStatusCode == SC_INTERNAL_SERVER_ERROR) {
            assertInternalServerError(response);
        } else {
            assertOrderErrorResponse(response, expectedStatusCode, expectedMessage);
        }
    }
}
