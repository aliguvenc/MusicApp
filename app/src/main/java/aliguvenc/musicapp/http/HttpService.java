package aliguvenc.musicapp.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by aliguvenc on 5.01.2018.
 */

public interface HttpService {

    @GET("genre")
    Call<GenreResponse> getGenres();

    @GET("genre/{id}/artists")
    Call<GenreResponse> getArtists(@Path("id") String id);

    @GET("artist/{id}/albums")
    Call<AlbumResponse> getAlbums(@Path("id") String id);

    @GET("album/{id}/tracks")
    Call<TrackResponse> getTracks(@Path("id") String id);

}
