package lesson5;

import com.github.javafaker.Faker;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductControllerTest {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @Test
    void getProductTest() throws IOException {

        Response<ResponseBody> response = productService.getProducts().execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }


    @Test
    void createModifyGetByIdDeleteProductTest() throws IOException {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));

        try {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(id));
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));

        product = new Product()
                .withId(id)
                .withTitle(faker.food().ingredient())
                .withPrice((int) (Math.random() * 10000))
                .withCategoryTitle("Food");
        Response<Product> response1 = productService.modifyProduct(product)
                .execute();
        assertThat(response1.isSuccessful(), CoreMatchers.is(true));

        Response<Product> response2 = productService.getProductById(id).execute();

            assertThat(response2.isSuccessful(), CoreMatchers.is(true));
            assertThat(response.body().getId(), equalTo(id));
        } finally {
            Response<ResponseBody> response3 = productService.deleteProduct(id).execute();
            assertThat(response3.isSuccessful(), CoreMatchers.is(true));
        }

    }
}




