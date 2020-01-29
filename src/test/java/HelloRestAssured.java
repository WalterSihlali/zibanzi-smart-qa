import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers.*;
import io.restassured.module.jsv.JsonSchemaValidator.*;
import org.junit.Assert;
import org.junit.Test;

public class HelloRestAssured extends  BaseURL {

    @Test
    public void GetWeatherDetails() {
        // Specify the base URL to the RESTful web service
        if(environment.equalsIgnoreCase("QA")) {
            RestAssured.baseURI = getQaBaseURL();
        } else {
            RestAssured.baseURI = intBaseURL;
        }
        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();
        // Make a GET request call directly by using RequestSpecification.get() method.
        // Make sure you specify the resource name.
        Response response = httpRequest.get("/Hyderabad");
        // Response.asString method will directly return the content of the body
        // as String.
        System.out.println("Response Body is =>  " + response.asString());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println("Status code : "+ statusCode);
    }
}
