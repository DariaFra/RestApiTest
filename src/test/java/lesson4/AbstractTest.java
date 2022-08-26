package lesson4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Properties;

public class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String urlForSearch;
    private static String urlForPost;
    private static String urlShoppingList;
    private static String hash;
    protected static RequestSpecification requestSpecification;
    protected static ResponseSpecification responseSpecification;
    protected static ObjectMapper objectMapper;


    @BeforeAll
    static void initTest() throws IOException {
        objectMapper = new ObjectMapper().findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig
                (new ObjectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> objectMapper));

        configFile = new FileInputStream(lesson3.AbstractTest.class.getResource("src/main/resources/properties")
                .getFile());
        prop.load(configFile);

        apiKey = prop.getProperty("apiKey");
        urlForSearch = prop.getProperty("urlForSearch");
        urlForPost = prop.getProperty("urlForPost");
        urlShoppingList = prop.getProperty("urlShoppingList");
        hash = prop.getProperty("hash");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .setContentType(ContentType.JSON)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .build();
        RestAssured.responseSpecification = responseSpecification;
        RestAssured.requestSpecification = requestSpecification;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getUrlForSearch() {
        return urlForSearch;
    }

    public static String getUrlForPost() {
        return urlForPost;
    }

    public static String getUrlShoppingList() {
        return urlShoppingList;
    }
    public static String getHash(){
        return hash;
    }
}
