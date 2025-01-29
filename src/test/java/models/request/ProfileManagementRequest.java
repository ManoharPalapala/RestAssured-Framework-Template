package models.request;

public class ProfileManagementRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;

    public ProfileManagementRequest(String firstName, String lastName, String email, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

//    Builder class

    public static class ProfileManagementBuilder{
        private String firstName;
        private String  lastName;
        private String   email;
        private String   mobileNumber;

        public ProfileManagementBuilder firstName(String firstName){
            this.firstName=firstName;
            return this;
        }
        public ProfileManagementBuilder lastName(String lastName){
            this.lastName=lastName;
            return this;
        }
        public ProfileManagementBuilder email(String email){
            this.email=email;
            return this;
        }
        public ProfileManagementBuilder mobileNumber(String mobileNumber){
            this.mobileNumber=mobileNumber;
            return this;
        }

        public ProfileManagementRequest build(){
            ProfileManagementRequest profileManagementRequest = new ProfileManagementRequest(firstName,lastName,email,mobileNumber);
            return profileManagementRequest;
        }
    }
}
