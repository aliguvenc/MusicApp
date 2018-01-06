package aliguvenc.musicapp.http;

import java.util.concurrent.TimeUnit;

import aliguvenc.musicapp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aliguvenc on 5.01.2018.
 */

public class RestController {

    private static RestController INSTANCE;
    private HttpService httpService;

    public static RestController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RestController();
        }
        return INSTANCE;
    }

    private RestController() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        int timeoutInterval=60;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(timeoutInterval, TimeUnit.SECONDS);
        httpClient.readTimeout(timeoutInterval, TimeUnit.SECONDS);

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .build();

        httpService = retrofit.create(HttpService.class);
    }

    public HttpService getHttpService() {
        return httpService;
    }
}
