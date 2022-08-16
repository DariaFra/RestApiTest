package lesson4;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TestRest extends AbstractTest {
    @Test
    void addRecipesTest_01() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title", "Nigerian Snail Stew")
                .post(getUrlForPost())
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));
    }

    @Test()
    void addRecipesTest_02() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title", "Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .formParam("ingredientList", "cauliflower, rice")
                .post(getUrlForPost())
                .then()
                .extract()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Chinese"));
    }

    @Test()
    void addRecipesTest_03() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title", "African Chicken Peanut Stew")
                .post(getUrlForPost())
                .then()
                .extract()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("African"));
    }

    @Test()
    void addRecipesTest_04() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title", "Berry Banana Breakfast Smoothie")
                .post(getUrlForPost())
                .then()
                .extract()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));
    }

    @Test()
    void addRecipesTest_05() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title", "Broccoli and Chickpea Rice Salad")
                .post(getUrlForPost())
                .then()
                .extract()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));
    }
}
