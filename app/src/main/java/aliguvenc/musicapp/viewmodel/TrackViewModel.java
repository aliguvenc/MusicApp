package aliguvenc.musicapp.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import aliguvenc.musicapp.BR;
import aliguvenc.musicapp.Communication;
import aliguvenc.musicapp.MusicApplication;
import aliguvenc.musicapp.http.Album;
import aliguvenc.musicapp.http.BaseCallback;
import aliguvenc.musicapp.http.RestController;
import aliguvenc.musicapp.http.TrackResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class TrackViewModel extends BaseObservable {

    public final ObservableBoolean isDataLoading;
    private Communication.DataListener<TrackResponse> dataListener;
    private Album album;

    @Bindable
    public String albumTitle;
    @Bindable
    public String imageUrl;

    public TrackViewModel(Communication.DataListener<TrackResponse> dataListener, Bundle arguments) {
        isDataLoading = new ObservableBoolean(false);
        this.dataListener = dataListener;
        album = (Album) arguments.getSerializable("album");
        getTracks(album.getId());
    }

    private void getTracks(String id) {
        isDataLoading.set(true);
        RestController.getInstance().getHttpService().getTracks(id).enqueue(new BaseCallback<TrackResponse>(isDataLoading) {
            @Override
            public void onResponse(@NonNull Call<TrackResponse> call, @NonNull Response<TrackResponse> response) {
                isDataLoading.set(false);
                if (response.isSuccessful() && !response.body().getData().isEmpty()) {
                    TrackResponse trackResponse = response.body();
                    trackResponse.setImage(album.getCover_big());
                    trackResponse.setAlbumTitle(album.getTitle());
                    setTrackResponse(trackResponse);
                    dataListener.onDataLoad(trackResponse);
                } else {
                    Toast.makeText(MusicApplication.getINSTANCE(), "Liste çekilemedi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setTrackResponse(TrackResponse trackResponse) {
        albumTitle = String.format("%s-%s", trackResponse.getData().get(0).getGenre().getName(), trackResponse.getAlbumTitle());
        imageUrl = trackResponse.getImage();
        notifyPropertyChanged(BR.albumTitle);
        notifyPropertyChanged(BR.imageUrl);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view,String url) {
        Picasso.with(view.getContext())
                .load(url)
                .into(view);
    }
}
