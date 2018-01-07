package aliguvenc.musicapp.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import aliguvenc.musicapp.Communication;
import aliguvenc.musicapp.http.Genre;
import aliguvenc.musicapp.view.AlbumFragment;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class ArtistRowViewModel extends BaseObservable {

    private Genre genre;
    private Communication.Item itemClickListener;

    public ArtistRowViewModel(Genre genre, Communication.Item itemClickListener) {
        this.genre = genre;
        this.itemClickListener = itemClickListener;
    }

    @Bindable
    public Genre getGenre() {
        return genre;
    }

    @Bindable
    public String getTitle() {
        return genre.getName();
    }

    @Bindable
    public String getImageUrl() {
        return genre.getPicture();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    public void onItemClick() {
        itemClickListener.onClick(AlbumFragment.newInstance(genre));
    }
}
