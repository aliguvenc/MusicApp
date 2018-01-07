package aliguvenc.musicapp.http;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class TrackResponse {

    private List<Track> data = new ArrayList<>();
    private String image;
    private String albumTitle;

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public void setData(List<Track> data) {
        this.data = data;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Track> getData() {
        return data;
    }

}
