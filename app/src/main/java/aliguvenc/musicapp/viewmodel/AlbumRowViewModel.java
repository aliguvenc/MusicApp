package aliguvenc.musicapp.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import aliguvenc.musicapp.helper.Communication;
import aliguvenc.musicapp.http.Album;
import aliguvenc.musicapp.http.Genre;
import aliguvenc.musicapp.view.TracksFragment;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class AlbumRowViewModel extends BaseObservable {

    private Album album;
    private Communication.Item itemClickListener;

    public AlbumRowViewModel(Album album, Communication.Item itemClickListener) {
        this. album= album;
        this.itemClickListener = itemClickListener;
    }

    @Bindable
    public Genre getGenre() {
        return album;
    }

    @Bindable
    public String getTitle() {
        return album.getTitle();
    }

    @Bindable
    public String getImageUrl() {
        return album.getCover_medium();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    public void onItemClick() {
        itemClickListener.onClick(TracksFragment.newInstance(album));
    }
}
