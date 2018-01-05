package aliguvenc.musicapp;

import java.util.concurrent.TimeUnit;

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

        int TIME_OUT = 60;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .build();

        httpService = retrofit.create(HttpService.class);
    }

    public HttpService getHttpService() {
        return httpService;
    }
}
