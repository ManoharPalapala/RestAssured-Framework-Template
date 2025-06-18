package base.services;


import filters.TestFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class BaseService { // wrapper class for Rest Assured because
    /*
    * contains Base Uri
    * Creating Request
    * Handling Response
    */

    private static final String BASE_URL = URL; // constant should contain final keyword and all final variables should be static

    private RequestSpecification requestSpecification; // created an instance variable of RequestSpecification Interface [objects cannot be created for Interfaces]

    static {
        filters(new TestFilter());
    }

    public BaseService() {
        requestSpecification = given().baseUri(BASE_URL); // as RestAssured is imported statically no need to mention RestAssured.given
    }

    protected void setAuthToken(String token){
        requestSpecification.header("Authorization","Bearer "+token);
    }

    protected Response postRequest(Object payload, String endpoint){
       return  requestSpecification.contentType(ContentType.JSON).body(payload).post(endpoint);
    }
    protected Response postRequest(String URL,Object payload, String endpoint){
        return  requestSpecification.baseUri(URL).contentType(ContentType.JSON).body(payload).post(endpoint);
    }

    protected Response putRequest(Object payload, String endpoint){
        return  requestSpecification.contentType(ContentType.JSON).body(payload).put(endpoint);
    }

    protected Response getRequest(String endpoint){
        return  requestSpecification.get(endpoint);
    }


}
