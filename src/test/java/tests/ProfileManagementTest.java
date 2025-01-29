package tests;

import base.services.AuthService;
import base.services.ProfileManagementService;
import io.restassured.response.Response;
import models.request.LoginRequest;
import models.request.ProfileManagementRequest;
import models.response.LoginResponse;
import models.response.ProfileManagementResponse;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(listeners.TestListener.class)
public class ProfileManagementTest {

    @Test(description = "Verify User Profile Management Response Using valid Authorization")
    public void getProfileManagement(){
// Profile management class requires AUTH token to access the response
        LoginRequest loginRequest = new LoginRequest("uday1234","uday1234");
        AuthService authService = new AuthService();
        Response response = authService.login(loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class);

        response = new ProfileManagementService().getProfile(loginResponse.getToken());
        ProfileManagementResponse profileManagementResponse = response.as(ProfileManagementResponse.class);
        System.out.println(response.asPrettyString());
    }

    @Test(description = "Verify User Profile Management Update Using valid Authorization")
    public void updateProfileManagement() {
        LoginRequest loginRequest = new LoginRequest("uday1234","uday1234");
        Response response = new AuthService().login(loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class);

        ProfileManagementRequest profileManagementRequest = new ProfileManagementRequest.ProfileManagementBuilder().
                firstName("Rohit").
                lastName("Roy").
                email("rohitroy199@mail.com").
                mobileNumber("9000000000").build();
        response = new ProfileManagementService().updateProfile(loginResponse.getToken(),profileManagementRequest);
        ProfileManagementResponse profileManagementResponse = response.as(ProfileManagementResponse.class);
        System.out.println(profileManagementResponse.getEmail());
    }


    }
