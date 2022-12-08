package tests;

import adapters.LoginAdapter;
import adapters.RegisterAdapter;
import adapters.ResourceAdapter;
import adapters.UsersAdapter;
import com.google.gson.Gson;
import models.User;
import objects.SingleUser;
import objects.SupportReqres;
import objects.UsersList;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.*;

public class reqresTest {

    @Test
    public void getListUsersTest() {
        String expectedName = "George";
        String body = new UsersAdapter().getListUsers().asString();
        UsersList usersList = new Gson().fromJson(body, UsersList.class);
        String firstUserName = usersList.getData().get(0).getFirstName();
        Assert.assertEquals(firstUserName, expectedName);
    }

    @Test
    public void getSingleUserTest() {
        String userId = "2";
        SingleUser user = SingleUser.builder().data(objects.User.builder()
                        .id(2)
                        .email("janet.weaver@reqres.in")
                        .firstName("Janet")
                        .lastName("Weaver")
                        .avatar("https://reqres.in/img/faces/2-image.jpg")
                .build())
                .support(SupportReqres.builder()
                        .url("https://reqres.in/#support-heading")
                        .text("To keep ReqRes free, contributions towards server costs are appreciated!").build()).build();
        String expectedUser = new UsersAdapter().convertUserToJson(user);
        String actualBody = new UsersAdapter().getSingleUser(userId).asString();
        Assert.assertEquals(actualBody, expectedUser);
    }

    @Test
    public void getSingleUserNotFoundTest() {
        String userId = "23";
        int statusCode = new UsersAdapter().getSingleUser(userId).statusCode();
        Assert.assertEquals(statusCode, HTTP_NOT_FOUND);
    }

    @Test
    public void getListResourceTest() {
        int statusCode = new ResourceAdapter().getResource().statusCode();
        Assert.assertEquals(statusCode, HTTP_OK);
    }

    @Test
    public void getSingleResourceTest() {
        String resourceID = "2";
        int statusCode = new ResourceAdapter().getSingleResource(resourceID).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK);
    }

    @Test
    public void getSingleResourceNotFoundTest() {
        String resourceID = "23";
        int statusCode = new ResourceAdapter().getSingleResource(resourceID).statusCode();
        Assert.assertEquals(statusCode, HTTP_NOT_FOUND);
    }

    @Test
    public void postCreateTest() {
        User user = User.builder().name("morpheus").job("leader").build();
        int statusCode = new UsersAdapter().postCreateUser(user).statusCode();
        Assert.assertEquals(statusCode, HTTP_CREATED);
    }

    @Test
    public void putUpdateTest() {
        String userId = "2";
        User user = User.builder().name("morpheus").job("zion resident").build();
        int statusCode = new UsersAdapter().putUpdateUser(userId, user).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK);
    }

    @Test
    public void patchUpdateTest() {
        String userId = "2";
        User user = User.builder().name("morpheus").job("zion resident").build();
        int statusCode = new UsersAdapter().patchUpdateUser(userId, user).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK);
    }

    @Test
    public void deleteUserTest() {
        String userId = "2";
        int statusCode = new UsersAdapter().deleteUser(userId).statusCode();
        Assert.assertEquals(statusCode, HTTP_NO_CONTENT);
    }

    @Test
    public void postRegisterSuccessfulTest() {
        User user = User.builder().email("eve.holt@reqres.in").password("pistol").build();
        int statusCode = new RegisterAdapter().postRegister(user).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK);
    }

    @Test
    public void postRegisterUnsuccessfulTest() {
        User user = User.builder().email("sydney@fife").build();
        int statusCode = new RegisterAdapter().postRegister(user).statusCode();
        Assert.assertEquals(statusCode, HTTP_BAD_REQUEST);
    }

    @Test
    public void loginSuccessfulTest() {
        User user = User.builder().email("eve.holt@reqres.in").password("cityslicka").build();
        int statusCode = new LoginAdapter().postLogin(user).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK);
    }

    @Test
    public void loginUnsuccessfulTest() {
        User user = User.builder().email("peter@klaven").build();
        int statusCode = new LoginAdapter().postLogin(user).statusCode();
        Assert.assertEquals(statusCode, HTTP_BAD_REQUEST);
    }

    @Test
    public void getDelayedResponseTest() {
        String delayedTime = "3";
        int statusCode = new UsersAdapter().getDelayed(delayedTime).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK);
    }
}
