package response;

import entity.Genre;
import io.qameta.allure.Attachment;
import io.restassured.internal.MultiValueEntity;
import io.restassured.internal.NameAndValue;
import io.restassured.response.Response;

import java.util.ArrayList;

public class BaseResponse<T> {
    protected Response response;
    private Class<T> responseClass;

    public BaseResponse(Response response, Class<T> responseClass) {
        this.response = response;
        this.responseClass = responseClass;
    }

    public int getStatusCode() {
        return this.response.getStatusCode();
    }

    public String getHeader(String header) {
        return this.response.getHeader(header);
    }

//    public T getBody() {
//        return this.response.body().as(this.responseClass);
//    }

    public T getBody() {
        return this.response.body().as(this.responseClass);
    }

}
