package aliguvenc.musicapp.http;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class GenreResponse {

    private List<Genre> data=new ArrayList<>();
    private String genreTitle;
    private String imageUrl;

    public String getGenreTitle() {
        return genreTitle;
    }

    public void setGenreTitle(String genreTitle) {
        this.genreTitle = genreTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Genre> getData() {
        return data;
    }
}
