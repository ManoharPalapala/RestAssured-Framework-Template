package base.services;

import io.restassured.response.Response;
import models.request.LoginRequest;
import models.request.SignupRequest;

import java.util.HashMap;

public class AuthService extends BaseService {

   private static final String BASE_PATH = "/api/auth/";

   public Response login(LoginRequest payload){
       return postRequest(payload,BASE_PATH +"login");
   }

    public Response signup(SignupRequest payload){
        return postRequest(payload,BASE_PATH +"signup");
    }

    public Response forgotPassword(String emailAddress){ // POJO class is not created as only 1 payload
        HashMap<String,String> payload = new HashMap<>();
        payload.put("email",emailAddress);
        return postRequest(payload,BASE_PATH +"signup");
    }
}
