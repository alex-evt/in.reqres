package adapters;


import io.restassured.response.Response;
import models.User;

import static utils.StringConstant.LOGIN_API_ENDPOINT;

public class LoginAdapter extends BaseAdapter{

    public Response postLogin(User user){
        return post(LOGIN_API_ENDPOINT, user);
    }

}
