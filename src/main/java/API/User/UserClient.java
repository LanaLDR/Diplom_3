package API.User;

import API.RestClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {
    private static final String CREATE_USER_PATH = "api/auth/register/";
    private static final String LOGIN_USER_PATH = "api/auth/login/";
    private static final String DELETE_USER_PATH = "api/auth/user/";

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(CREATE_USER_PATH)
                .then();
    }

    @Step("Логин пользователя")
    public ValidatableResponse loginUser(User user) {
        UserCredentials userCredentials = UserCredentials.from(user);
        return given()
                .spec(getBaseSpec())
                .body(userCredentials)
                .when()
                .post(LOGIN_USER_PATH)
                .then();
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
                given()
                .spec(getBaseSpec())
                .header("authorization", accessToken)
                .when()
                .delete(DELETE_USER_PATH)
                .then();
    }
}
