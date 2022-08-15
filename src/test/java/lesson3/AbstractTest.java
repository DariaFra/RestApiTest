package lesson3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

public abstract class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String urlForSearch;
    private static String urlForPost;

    @BeforeAll
    static void initTest() throws IOException {
        configFile = new FileInputStream(AbstractTest.class.getResource("../properties").getFile());
        prop.load(configFile);

        apiKey = prop.getProperty("apiKey");
        urlForSearch = prop.getProperty("urlForSearch");
        urlForPost = prop.getProperty("urlForPost");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
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
}
