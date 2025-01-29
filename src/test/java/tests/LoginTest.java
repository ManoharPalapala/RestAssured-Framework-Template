package tests;

import base.services.AuthService;
import io.restassured.response.Response;
import models.request.LoginRequest;
import models.request.SignupRequest;
import models.response.LoginResponse;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(listeners.TestListener.class)
public class LoginTest {

    @Test(description = "Verify Login with Valid Payload")
    public void loginTest(){
        LoginRequest loginPayload = new LoginRequest("uday1234","uday1234"); //POJO class
        AuthService authService = new AuthService();
        Response response = authService.login(loginPayload);
        LoginResponse loginResponse = response.as(LoginResponse.class);
    }

}
