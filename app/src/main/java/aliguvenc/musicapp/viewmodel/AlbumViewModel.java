package aliguvenc.musicapp.viewmodel;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.widget.Toast;

import aliguvenc.musicapp.Communication;
import aliguvenc.musicapp.MusicApplication;
import aliguvenc.musicapp.http.AlbumResponse;
import aliguvenc.musicapp.http.BaseCallback;
import aliguvenc.musicapp.http.RestController;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class AlbumViewModel {

    public final ObservableBoolean isDataLoading;
    private Communication.DataListener<AlbumResponse> dataListener;

    public AlbumViewModel(Communication.DataListener<AlbumResponse> dataListener) {
        isDataLoading = new ObservableBoolean(false);
        this.dataListener = dataListener;
    }

    public void getAlbums(String id) {
        isDataLoading.set(true);
        RestController.getInstance().getHttpService().getAlbums(id).enqueue(new BaseCallback<AlbumResponse>(isDataLoading) {
            @Override
            public void onResponse(@NonNull Call<AlbumResponse> call, @NonNull Response<AlbumResponse> response) {
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
