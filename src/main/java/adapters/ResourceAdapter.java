package adapters;

import io.restassured.response.Response;

import static utils.StringConstant.ID_API_ENDPOINT;
import static utils.StringConstant.RESOURCE_API_ENDPOINT;

public class ResourceAdapter extends BaseAdapter {

    public Response getResource(){
        return get(RESOURCE_API_ENDPOINT);
    }

    public Response getSingleResource(String resourceId){
        return get(RESOURCE_API_ENDPOINT + String.format(ID_API_ENDPOINT, resourceId));
    }
}
