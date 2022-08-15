package lesson3;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class TestRest extends AbstractTest {

    @Test
    void getRequestDataTest_01() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("pasta")
                .queryParam("sort", "calories")
                .queryParam("minCarbs", 40)
                .when()
                .get(getUrlForSearch())
                .then()
                .statusCode(200)
                .body(new StringContains("Carbohydrates"))
                .body(new StringContains("Calories"));


        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Garlicky Kale")
                .when()
                .post(getUrlForPost())
                .then()
                .statusCode(200)
                .body(new StringContains("Italian"));
    }

    @Test
    void getRequestDataTest_02() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("excludeCuisine", "greek")
                .queryParam("diet", "vegetarian")
                .queryParam("minVitaminE", 15)
                .when()
                .get(getUrlForSearch())
                .then()
                .statusCode(200)
                .body(new StringContains("Vitamin E"));
    }

    @Test
    void getRequestDataTest_03() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("excludeIngredients", "cheese")
                .queryParam("burger")
                .queryParam("maxCarbs", 55)
                .when()
                .get(getUrlForSearch())
                .then()
                .statusCode(200)
                .body(new StringContains("Carbohydrates"));
    }

    @Test
    void getRequestDataTest_04() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("excludeCuisine", "American")
                .queryParam("diet", "vegetarian")
                .queryParam("type", "appetizer")
                .queryParam("sort", "thealthiness")
                .queryParam("maxCalories", 300)
                .when()
                .get(getUrlForSearch())
                .then()
                .statusCode(200)
                .body(new StringContains("Calories"));
    }

    @Test
    void getRequestDataTest_05() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("type", "drink")
                .queryParam("minAlcohol", 10)
                .queryParam("maxAlcohol", 40)
                .when()
                .get(getUrlForSearch())
                .then()
                .statusCode(200)
                .body(new StringContains("Alcohol"));
    }

    @Test
    void addRecipesTest_01() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Nigerian Snail Stew")
                .when()
                .post(getUrlForPost())
                .then()
                .statusCode(200)
                .body(new StringContains("Italian"));
    }

    @Test()
    void addRecipesTest_02() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .formParam("ingredientList", "cauliflower, rice")
                .when()
                .post(getUrlForPost())
                .then()
                .statusCode(200)
                .body(new StringContains("Chinese"));
    }

    @Test()
    void addRecipesTest_03() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "African Chicken Peanut Stew")
                .when()
                .post(getUrlForPost())
                .then()
                .statusCode(200)
                .body(new StringContains("African"));
    }

    @Test()
    void addRecipesTest_04() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Berry Banana Breakfast Smoothie")
                .when()
                .post(getUrlForPost())
                .then()
                .statusCode(200)
                .body(new StringContains("Italian"));
    }

    @Test()
    void addRecipesTest_05() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Broccoli and Chickpea Rice Salad")
                .when()
                .post(getUrlForPost())
                .then()
                .statusCode(200)
                .body(new StringContains("Italian"));
    }

    @Test
    void addToShoppingListTest() {
        String id = given()
                .queryParam("hash", "ddc3bf752c0c7dbd3f7394f3790115582f6d5019")
                .queryParam("apiKey", getApiKey())
                //.pathParams("username", "your-users-name250")
                .body("{\n"
                        + " \"item\": 1 package baking powder,\n"
                        + " \"aisle\": Baking,\n"
                        + " \"parse\": true,\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/your-users-name250/shopping-list/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
        given()
                .queryParam("hash", "ddc3bf752c0c7dbd3f7394f3790115582f6d5019")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/your-users-name250/shopping-list/items/" + id)
                .then()
                .statusCode(200);
    }
}

