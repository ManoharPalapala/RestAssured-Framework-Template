package tests;

import base.services.AuthService;
import io.restassured.response.Response;
import models.request.SignupRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupTest {

    @Test(description = "Verify Account Creation with Valid Payload")
    public void signUpTest(){
        SignupRequest signupRequest = new SignupRequest.SignupBuilder().userName("disha123")
                .password("disha123")
                .email("disha@yahoo.com")
                .firstname("Disha")
                .lastname("Bhatt")
                .mobileNumber("7777777776").build();

        AuthService authService = new AuthService();
        Response response = authService.signup(signupRequest);
        System.out.println(response.asPrettyString());
//        Assert.assertEquals(response.asPrettyString(),);

    }
}
