package lesson4;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class AddToShoppingListTest extends AbstractTest {

    @Test
    void createObject() {

        AddToShoppingListRequest shoppingRequest = new AddToShoppingListRequest();
        shoppingRequest.setItem("1 package baking powder");
        shoppingRequest.setAisle("Baking");
        shoppingRequest.setParse(true);


        AddToShoppingListRequest shoppingRequest2 = AddToShoppingListRequest.builder()
                .item("1 package baking powder")
                .aisle("Baking")
                .parse(true)
                .build();


        Assertions.assertEquals(shoppingRequest, shoppingRequest2);
        Assertions.assertFalse(shoppingRequest == shoppingRequest2);
    }

    @Test
    void addToShoppingListTest() throws JsonProcessingException {
        AddToShoppingListRequest shoppingRequest2 = AddToShoppingListRequest.builder()
                .item("baking powder")
                .aisle("Baking")
                .parse(true)
                .build();


        AddToShoppingListResponse idName = given()
                .queryParam("hash", "ddc3bf752c0c7dbd3f7394f3790115582f6d5019")
                .queryParam("apiKey", getApiKey())
                .body(shoppingRequest2)
                .when()
                .post(getUrlShoppingList() + "/items")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AddToShoppingListResponse.class);
        assertThat(idName.getName(), containsString("baking powder"));
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", "ddc3bf752c0c7dbd3f7394f3790115582f6d5019")
                .when()
                .get(getUrlShoppingList())
                .then()
                .statusCode(200);

        given()
                .queryParam("hash", "ddc3bf752c0c7dbd3f7394f3790115582f6d5019")
                .queryParam("apiKey", getApiKey())
                .delete(getUrlShoppingList() + "/items/" + idName.getId())
                .then()
                .statusCode(200);
    }
}

