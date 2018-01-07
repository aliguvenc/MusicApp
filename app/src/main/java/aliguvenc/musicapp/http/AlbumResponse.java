package aliguvenc.musicapp.http;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class AlbumResponse {

    private List<Album> data=new ArrayList<>();
    private String artistTitle;
    private String imageUrl;

    public String getArtistTitle() {
        return artistTitle;
    }

    public void setArtistTitle(String artistTitle) {
        this.artistTitle = artistTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Album> getData() {
        return data;
    }

}
