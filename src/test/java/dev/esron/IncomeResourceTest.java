package dev.esron;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
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
            .when().post("/incomes")
            .then()
                .statusCode(400);

        given()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .body("{\"description\": \"\", \"value\": \"\", \"date\": \"invalid\"}")
            .when().post("/incomes")
            .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("title", equalTo("Constraint Violation"))
                .body("violations.find { it.field == 'create.income.description' }.message", equalTo("must not be blank"))
                .body("violations.find { it.field == 'create.income.value' }.message", equalTo("must not be null"))
                .body("violations.find { it.field == 'create.income.date' }.message", equalTo("invalid date format"));
    }
}
