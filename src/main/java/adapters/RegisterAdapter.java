package adapters;

import io.restassured.response.Response;
import models.User;

import static utils.StringConstant.REGISTER_API_ENDPOINT;

public class RegisterAdapter extends BaseAdapter{

    public Response postRegister(User user){
        return post(REGISTER_API_ENDPOINT, user);
    }
}
