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
import aliguvenc.musicapp.http.AlbumResponse;
import aliguvenc.musicapp.http.BaseCallback;
import aliguvenc.musicapp.http.Genre;
import aliguvenc.musicapp.http.RestController;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class AlbumViewModel extends BaseObservable {

    public final ObservableBoolean isDataLoading;
    private Communication.DataListener<AlbumResponse> dataListener;
    private Genre genre;
    @Bindable
    public String artistTitle;
    @Bindable
    public String imageUrl;

    public AlbumViewModel(Communication.DataListener<AlbumResponse> dataListener, Bundle args) {
        isDataLoading = new ObservableBoolean(false);
        this.dataListener = dataListener;
        genre = (Genre) args.getSerializable("genre");
        getAlbums(genre.getId());
    }

    public void getAlbums(String id) {
        isDataLoading.set(true);
        RestController.getInstance().getHttpService().getAlbums(id).enqueue(new BaseCallback<AlbumResponse>(isDataLoading) {
            @Override
            public void onResponse(@NonNull Call<AlbumResponse> call, @NonNull Response<AlbumResponse> response) {
                isDataLoading.set(false);
                if (response.isSuccessful() && !response.body().getData().isEmpty()) {
                    AlbumResponse albumResponse = response.body();
                    albumResponse.setArtistTitle(genre.getName());
                    albumResponse.setImageUrl(genre.getPicture_big());
                    setTrackResponse(albumResponse);
                    dataListener.onDataLoad(response.body());
                } else {
                    Toast.makeText(MusicApplication.getINSTANCE(), "Liste çekilemedi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setTrackResponse(AlbumResponse albumResponse) {
        artistTitle = albumResponse.getArtistTitle();
        imageUrl = albumResponse.getImageUrl();
        notifyPropertyChanged(BR.artistTitle);
        notifyPropertyChanged(BR.imageUrl);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .into(view);
    }
}
