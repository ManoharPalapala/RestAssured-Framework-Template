package models.request;

public class SignupRequest {

    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String mobileNumber;

    public SignupRequest(String username, String password, String email, String firstname, String lastname, String mobileNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }

    //  Creating in-class object using Builder design pattern, as there are large set of attributes to be passed in the constructor
    public static class SignupBuilder{
        private String username;
        private String password;
        private String email;
        private String firstname;
        private String lastname;
        private String mobileNumber;

//        Created setters for all the variables
        public SignupBuilder userName(String username){
            this.username = username;
            SignupBuilder current_username = this;
            return current_username;
        }

        public SignupBuilder password(String password){
            this.password = password;
            return this;
        }

        public SignupBuilder email(String email){
            this.email = email;
            return this;
        }

        public SignupBuilder firstname(String firstname){
            this.firstname = firstname;
            return this;
        }

        public SignupBuilder lastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public SignupBuilder mobileNumber(String mobileNumber){
            this.mobileNumber = mobileNumber;
            return this;
        }

//         This build method is responsible for setting the variable values
        public SignupRequest build(){
            SignupRequest signupBuilder = new SignupRequest(username, password, email, firstname, lastname, mobileNumber);
            return signupBuilder;
        }
    }

}
