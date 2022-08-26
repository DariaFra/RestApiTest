package lesson5;

import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class RetrofitUtils {
    static Properties prop = new Properties();
    private static InputStream configFile;

    static {
        try {
            configFile = new FileInputStream("src/main/resources/properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @SneakyThrows
    public static String getBaseUrl() {
        prop.load(configFile);
        return prop.getProperty("url");
    }

    static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    static LoggingInterceptor logging2 = new LoggingInterceptor();
    static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    public static Retrofit getRetrofit() {
        logging.setLevel(BODY);
        httpClient.addInterceptor(logging2);
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
