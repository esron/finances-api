package dev.esron;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class IncomeResourceTest {

    @Test
    public void testCreateIncomeValidationTest() {
        given()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .body("{\"description\": \"\", \"value\": \"\", \"date\": \"invalid\"}")
            .when().post("/incomes")
            .then()
                .statusCode(StatusCode.BAD_REQUEST)
                .contentType(ContentType.JSON)
                .body("title", equalTo("Constraint Violation"))
                .body("violations.find { it.field == 'create.income.description' }.message", equalTo("must not be blank"))
                .body("violations.find { it.field == 'create.income.value' }.message", equalTo("must not be null"))
                .body("violations.find { it.field == 'create.income.date' }.message", equalTo("invalid date format"));
    }

    @Test
    public void testCreateIncome() {
        given()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .body("{\"description\": \"water bill\", \"value\": 2445, \"date\": \"02/08/2022\"}")
            .when().post("/incomes")
            .then()
                .statusCode(StatusCode.CREATED)
                .contentType(ContentType.JSON)
                .body("description", equalTo("water bill"))
                .body("value", equalTo(2445))
                .body("date", equalTo("02/08/2022"));
    }
}
