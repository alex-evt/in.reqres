package adapters;

import com.google.gson.Gson;
import io.restassured.response.Response;
import models.User;

import static io.restassured.RestAssured.given;
import static utils.StringConstant.*;

public abstract class BaseAdapter {

    protected Gson converter = new Gson();

    protected Response get(String url) {
        return
                given()
                        .header(CONTENT_TYPE, JSON)
                        .when()
                        .get(BASE_URL + url)
                        .then()
                        .log().all()
                        .extract().response();
    }

    protected Response post(String url, User user) {
        return
                given()
                        .header(CONTENT_TYPE, JSON)
                        .body(user)
                        .when()
                        .post(BASE_URL + url)
                        .then()
                        .log().all()
                        .extract().response();
    }

    protected Response put(String url, User user) {
        return given()
                .header(CONTENT_TYPE, JSON)
                .body(user)
                .when()
                .put(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response patch(String url, User user) {
        return
                given()
                        .header(CONTENT_TYPE, JSON)
                        .body(user)
                        .when()
                        .patch(BASE_URL + url)
                        .then()
                        .log().all()
                        .extract().response();
    }

    protected Response delete(String url) {
        return
                given()
                        .when()
                        .delete(BASE_URL + url)
                        .then()
                        .log().all()
                        .extract().response();
    }
}
