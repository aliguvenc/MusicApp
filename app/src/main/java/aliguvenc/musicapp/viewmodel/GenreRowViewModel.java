package aliguvenc.musicapp.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import aliguvenc.musicapp.http.Genre;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class GenreRowViewModel extends BaseObservable {

    private Genre genre;

    public GenreRowViewModel(Genre genre) {
        this.genre = genre;
    }

    @Bindable
    public Genre getGenre(){
        return genre;
    }

    @Bindable
    public String getTitle(){
        return genre.getName();
    }

    @Bindable
    public String getImageUrl(){
        return genre.getPicture();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
