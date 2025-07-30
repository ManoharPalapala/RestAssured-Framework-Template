# RestAssured API Testing Framework
A robust and scalable REST API testing framework built with Java, RestAssured, TestNG, and Maven. This framework follows industry best practices with service-oriented architecture, custom filters, listeners, and comprehensive request/response modeling.
## 🚀 Features
- Service-Oriented Architecture: Clean separation with dedicated service classes for different API modules
- Modular Design: Extensible architecture for adding new API modules
- Request/Response Modeling: POJO classes with Builder pattern for complex request payloads
- Custom Filters: Comprehensive request/response logging and interception
- Token-Based Authentication: Built-in support for JWT/Bearer token authentication 
- Log4j2 Logging: Structured logging with detailed request/response tracking


## 📋 Prerequisites
- #### Java 8+ - [Download Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- #### Maven 3.6+ - [Download Maven](https://maven.apache.org/download.cgi)
- #### IDE (IntelliJ IDEA/Eclipse)


## 🏗️ Project Architecture

    RestAssured-Framework-Template/
    ├── .github/
    │   └── workflows/                 # CI/CD workflows
    ├── src/
    │   └── test/
    │       ├── java/
    │       │   ├── base/
    │       │   │   └── services/
    │       │   │       ├── BaseService.java      # Common HTTP methods
    │       │   │       ├── AuthService.java      # Authentication API service
    │       │   │       └── ProfileManagementService.java  # Profile management APIs
    │       │   ├── filters/
    │       │   │   └── TestFilter.java           # Custom request/response filter
    │       │   ├── listeners/
    │       │   │   └── TestListener.java         # TestNG event listener
    │       │   ├── models/
    │       │   │   ├── request/
    │       │   │   │   ├── LoginRequest.java     # Login payload model
    │       │   │   │   ├── SignupRequest.java    # Signup payload model
    │       │   │   │   └── ProfileManagementRequest.java  # Profile update model
    │       │   │   └── response/
    │       │   │       ├── LoginResponse.java    # Login response model
    │       │   │       └── ProfileManagementResponse.java  # Profile response model
    │       │   └── tests/
    │       │       └── ProfileManagementTest.java    # Sample test implementation
    │       └── resources/
    │           ├── log4j2.xml                    # Logging configuration
    │           └── testng.xml                    # TestNG suite configuration
    ├── pom.xml
    └── README.md

## 🛠️ Key Components
### Service Layer Architecture
#### BaseService: 
The foundation class that provides common HTTP methods:

    public class BaseService {
    
    // Common methods: GET, POST, PUT, DELETE
    // Authentication token management
    // Request specification setup
    }

#### AuthService
Handles authentication-related API calls:
- Login: User authentication with credentials 
- Signup: New user registration 
- Forgot Password: Password reset functionality
#### ProfileManagementService
Manages user profile operations:
- Get Profile: Retrieve user profile information
- Update Profile: Modify user profile data
### Request/Response Models
#### Request Models with Builder Pattern
// Example: ProfileManagementRequest with Builder

    ProfileManagementRequest request = new ProfileManagementRequest.ProfileManagementBuilder()
    .firstName("John")
    .lastName("Doe")
    .email("john.doe@example.com")
    .mobileNumber("1234567890")
    .build();

#### Response Models
- LoginResponse: Contains authentication token and user details 
- ProfileManagementResponse: User profile information structure
### Advanced Features
#### Custom Test Filter
Intercepts and logs all HTTP requests/responses:
@Override
public Response filter(FilterableRequestSpecification requestSpec, 
                      FilterableResponseSpecification responseSpec, 
                      FilterContext ctx) {
    logRequest(requestSpec);
    Response response = ctx.next(requestSpec, responseSpec);
    logResponse(response);
    return response;
}

#### TestNG Listener
Provides detailed test execution tracking:
Test suite start/finish eventsIndividual test lifecycle managementComprehensive logging for debugging
## 🔧 Setup Instructions
### 1. Clone the Repository
    git clone https://github.com/ManoharPalapala/RestAssured-Framework-Template.git
    cd RestAssured-Framework-Template

### 2. Install Dependencies
    mvn clean install

### 3. Configure Base URL
    BASE_URL = "URL";

### 4. Run Tests
#### Run all tests
    mvn test

#### Run specific test class
    mvn test -Dtest=ProfileManagementTest

#### Run with TestNG XML
    mvn test -DsuiteXmlFile=testng.xml

## 🧪 Test Examples
### Authentication Flow Test
```
@Test(description = "Verify User Profile Management Response Using valid Authorization")

public void getProfileManagement(){
    // Step 1: Login to get authentication token
    LoginRequest loginRequest = new LoginRequest("username", "password");
    AuthService authService = new AuthService();
    Response response = authService.login(loginRequest);
    LoginResponse loginResponse = response.as(LoginResponse.class);
    
    // Step 2: Use token to access protected endpoint
    response = new ProfileManagementService().getProfile(loginResponse.getToken());
    ProfileManagementResponse profileResponse = response.as(ProfileManagementResponse.class);
    
    // Assertions and validations
    System.out.println(response.asPrettyString());
} 
```

### Profile Update with Builder Pattern
```
@Test(description = "Verify User Profile Management Update Using valid Authorization")
public void updateProfileManagement() {
    // Authentication
    LoginRequest loginRequest = new LoginRequest("username", "password");
    Response response = new AuthService().login(loginRequest);
    LoginResponse loginResponse = response.as(LoginResponse.class);
    
    // Build profile update request using Builder pattern
    ProfileManagementRequest profileRequest = new ProfileManagementRequest.ProfileManagementBuilder()
        .firstName("Rohit")
        .lastName("Roy")
        .email("rohitroy199@mail.com")
        .mobileNumber("9000000000")
        .build();
    
    // Execute profile update
    response = new ProfileManagementService().updateProfile(loginResponse.getToken(), profileRequest);
    ProfileManagementResponse profileResponse = response.as(ProfileManagementResponse.class);
    
    System.out.println(profileResponse.getEmail());
}
```


## 📊 Essential Dependencies


## 🔐 Authentication Architecture
### Token-Based Authentication Flow
- Login Request: Send credentials to /api/auth/login
- Token Extraction: Extract JWT/Bearer token from login response
- Token Storage: Store token in service class for subsequent requests
- Protected Requests: Include token in Authorization header for protected endpoints
### Authentication Implementation
public class BaseService {
    private String authToken;
    
    protected void setAuthToken(String token) {
        this.authToken = token;
        // Set in request specification
    }
    
    protected Response getRequest(String endpoint) {
        return given()
            .header("Authorization", "Bearer " + authToken)
            .when()
            .get(endpoint);
    }
}

## 📈 Logging and Monitoring
### Log4j2 Configuration
The framework uses Log4j2 for comprehensive logging:
Request Logging: Complete request details (URL, headers, body)Response Logging: Status codes, headers, response bodyTest Execution Logging: Test lifecycle events and results
### Filter-Based Logging
All HTTP traffic is automatically logged through the custom TestFilter:
INFO  - Request BASE_URL: https://api.example.com
INFO  - Request Headers: [Authorization=Bearer xxx, Content-Type=application/json]
INFO  - Request Body: {"username":"user","password":"pass"}
INFO  - Response status: 200
INFO  - Response Body: {"token":"jwt_token","user":{"id":1,"name":"John"}}

## 🚀 CI/CD Integration
### GitHub Actions Workflow
name: API Test Automation

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  api-tests:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
        
    - name: Cache Maven dependencies
      uses: actions/cache@v3
      with:
        path: ~/.m2
        key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
        
    - name: Run API Tests
      run: mvn clean test
      
    - name: Upload Test Reports
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: test-reports
        path: target/surefire-reports/

## 📋 Best Practices Implemented
### 1. Builder Pattern for Complex Objects
Fluent API for creating request payloadsImproved readability and maintainabilityValidation at build time
### 2. Service Layer Separation
Clean separation of concernsReusable service methodsEasy maintenance and testing
### 3. Comprehensive Error Handling
Proper exception handling in servicesDetailed error loggingGraceful failure management
### 4. Token Management
Secure token handlingAutomatic token injectionSession management
### 5. Logging Strategy
Request/Response loggingTest execution trackingDebug-friendly output
## 📝 Adding New Test Modules
### Step 1: Create Request/Response Models
// Create new request model in models/request/
public class NewFeatureRequest {
    // Add fields and Builder pattern
}

// Create corresponding response model in models/response/
public class NewFeatureResponse {
    // Add response fields with getters/setters
}

### Step 2: Create Service Class
public class NewFeatureService extends BaseService {
    private static final String BASE_PATH = "/api/new-feature/";
    
    public Response createNewFeature(Object payload) {
        return postRequest(payload, BASE_PATH + "create");
    }
    
    public Response getNewFeature(String id) {
        return getRequest(BASE_PATH + id);
    }
}

### Step 3: Write Test Class
@Listeners(listeners.TestListener.class)
public class NewFeatureTest {
    @Test(description = "Test new feature functionality")
    public void testNewFeature() {
        // Implement test logic
    }
}

## 🐛 Troubleshooting
### Common Issues

Authentication Token Expired
Check token validity periodImplement token refresh mechanismVerify login credentials

Request/Response Serialization Issues
Ensure POJO classes have default constructorsCheck Jackson annotationsVerify field naming conventions

Base URL Configuration
Verify API endpoint URLsCheck network connectivityValidate SSL certificates

### Debug Mode
Enable detailed RestAssured logging:
RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

## 🤝 Contributing
Fork the repositoryCreate a feature branch (git checkout -b feature/new-api-module)Follow the established patterns for services and modelsAdd appropriate tests and documentationCommit your changes (git commit -m 'Add new API module')Push to the branch (git push origin feature/new-api-module)Open a Pull Request
## 📧 Contact
Author: Manohar PalapalaGitHub: [@ManoharPalapala](https://github.com/ManoharPalapala)Email: Contact via GitHub profile
## 📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

⭐ If you find this framework helpful, please star the repository!
## 🔖 Quick Start Checklist
[ ] Clone the repository[ ] Update base URL in BaseService[ ] Configure authentication credentials[ ] Run sample ProfileManagementTest[ ] Check logs in console output[ ] Extend framework with new API modules[ ] Set up CI/CD pipeline
Happy API Testing! 🚀