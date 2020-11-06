package pl.edu.pjwstk.jaz.average;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AverageControllerIT {
    @Test
    public void shouldCalculateSingleAverage() {
        var test1 = given()
                .param("numbers", "1,2,3,4")
                .when()
                .get("/api/average")
                .then()
                .statusCode(200)
                .body(equalTo("Average is: 2.5"));
    }

    @Test
    public void shouldCalculateNoDecimal() {
        var test1 = given()
                .param("numbers", "10,20,5,0,0")
                .when()
                .get("/api/average")
                .then()
                .statusCode(200)
                .body(equalTo("Average is: 7"));
    }

    @Test
    public void shouldCalculateWithOnlyOneParam() {
        var test1 = given()
                .param("numbers", "2")
                .when()
                .get("/api/average")
                .then()
                .statusCode(200)
                .body(equalTo("Average is: 2"));
    }

    @Test
    public void shouldCalculateTwoDecimalPts() {
        var test1 = given()
                .param("numbers", "2,1,1")
                .when()
                .get("/api/average")
                .then()
                .statusCode(200)
                .body(equalTo("Average is: 1.33"));
    }

    @Test
    public void shouldAskForProperParams() {
        var test1 = given()
                .param("numbers", "")
                .when()
                .get("/api/average")
                .then()
                .statusCode(200)
                .body(equalTo("Please put parameters."));
    }
    @Test
    public void shouldNoRequireParameterGivenReturn400Error() {
        var test1 = given()
                .param( "cucumber")
                .when()
                .get("/api/average")
                .then()
                .statusCode(equalTo(400));
    }

}
