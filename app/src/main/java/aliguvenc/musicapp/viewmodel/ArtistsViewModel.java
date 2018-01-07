package aliguvenc.musicapp.viewmodel;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.widget.Toast;

import aliguvenc.musicapp.Communication;
import aliguvenc.musicapp.MusicApplication;
import aliguvenc.musicapp.http.BaseCallback;
import aliguvenc.musicapp.http.GenreResponse;
import aliguvenc.musicapp.http.RestController;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class ArtistsViewModel {

    public final ObservableBoolean isDataLoading;
    private Communication.DataListener<GenreResponse> dataListener;

    public ArtistsViewModel(Communication.DataListener<GenreResponse> dataListener) {
        isDataLoading = new ObservableBoolean(false);
        this.dataListener = dataListener;
    }

    public void getArtists(String id) {
        isDataLoading.set(true);
        RestController.getInstance().getHttpService().getArtists(id).enqueue(new BaseCallback<GenreResponse>(isDataLoading) {
            @Override
            public void onResponse(@NonNull Call<GenreResponse> call, @NonNull Response<GenreResponse> response) {
                isDataLoading.set(false);
                if (response.isSuccessful() && !response.body().getData().isEmpty()) {
                    dataListener.onDataLoad(response.body());
                } else {
                    Toast.makeText(MusicApplication.getINSTANCE(), "Liste çekilemedi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
