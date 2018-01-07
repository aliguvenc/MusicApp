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
import aliguvenc.musicapp.helper.Communication;
import aliguvenc.musicapp.helper.MusicApplication;
import aliguvenc.musicapp.http.BaseCallback;
import aliguvenc.musicapp.http.Genre;
import aliguvenc.musicapp.http.GenreResponse;
import aliguvenc.musicapp.http.RestController;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class ArtistsViewModel extends BaseObservable {

    public final ObservableBoolean isDataLoading;
    private Communication.DataListener<GenreResponse> dataListener;
    private Genre genre;

    @Bindable
    public String genreTitle;
    @Bindable
    public String imageUrl;

    public ArtistsViewModel(Communication.DataListener<GenreResponse> dataListener, Bundle args) {
        isDataLoading = new ObservableBoolean(false);
        this.dataListener = dataListener;
        genre = (Genre) args.getSerializable("genre");
        getArtists(genre.getId());
    }

    private void getArtists(String id) {
        isDataLoading.set(true);
        RestController.getInstance().getHttpService().getArtists(id).enqueue(new BaseCallback<GenreResponse>(isDataLoading) {
            @Override
            public void onResponse(@NonNull Call<GenreResponse> call, @NonNull Response<GenreResponse> response) {
                isDataLoading.set(false);
                if (response.isSuccessful() && !response.body().getData().isEmpty()) {
                    GenreResponse genreResponse = response.body();
                    genreResponse.setGenreTitle(genre.getName());
                    genreResponse.setImageUrl(genre.getPicture_big());
                    setTrackResponse(genreResponse);
                    dataListener.onDataLoad(genreResponse);
                } else {
                    Toast.makeText(MusicApplication.getINSTANCE(), "Liste çekilemedi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setTrackResponse(GenreResponse genreResponse) {
        genreTitle = genreResponse.getGenreTitle();
        imageUrl = genreResponse.getImageUrl();
        notifyPropertyChanged(BR.genreTitle);
        notifyPropertyChanged(BR.imageUrl);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .into(view);
    }
}
