package adapters;

import io.restassured.response.Response;
import models.User;
import objects.SingleUser;

import static utils.StringConstant.*;

public class UsersAdapter extends BaseAdapter {

    public String convertUserToJson(SingleUser user){
        return converter.toJson(user);
    }

    public Response getListUsers() {
        return get(USERS_API_ENDPOINT);
    }

    public Response getSingleUser(String userId) {
        return get(USERS_API_ENDPOINT + String.format(ID_API_ENDPOINT, userId));
    }

    public Response postCreateUser(User user){
        return post(USERS_API_ENDPOINT, user);
    }

    public Response putUpdateUser(String userId, User updatedData){
        return put(USERS_API_ENDPOINT + String.format(ID_API_ENDPOINT, userId), updatedData);
    }

    public Response patchUpdateUser(String userId, User updatedData){
        return patch(USERS_API_ENDPOINT + String.format(ID_API_ENDPOINT, userId), updatedData);
    }

    public Response deleteUser(String userId){
        return delete(USERS_API_ENDPOINT + String.format(ID_API_ENDPOINT, userId));
    }

    public Response getDelayed(String delaySeconds){
        return get(USERS_API_ENDPOINT + DELAY + delaySeconds);
    }
}
