package filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(TestFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        logRequest(requestSpec); // intercept request details from logRequest method
        Response response=ctx.next(requestSpec,responseSpec);   // passes the request to server and stores the response
        logResponse(response);

        return response;
    }

    public void logRequest(FilterableRequestSpecification request){
        logger.info("Request BASE_URL: "+request.getBaseUri());
        logger.info("Request Headers: "+request.getHeaders());
        logger.info("Request Body: "+request.<String>getBody());
        logger.info("---------------------------------------------------");
    }

    public void logResponse(Response response){
        logger.info("Response status: "+response.getStatusCode());
        logger.info("Response Headers: "+response.headers());
        logger.info("Response Body: "+response.getBody().prettyPrint());
    }
}
