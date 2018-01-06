package aliguvenc.musicapp.http;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by aliguvenc on 5.01.2018.
 */

public interface HttpService {

    @GET("genre")
    Call<GenreResponse> getGenres();
}
