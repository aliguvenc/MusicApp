package aliguvenc.musicapp.http;

/**
 * Created by aliguvenc on 5.01.2018.
 */

public class Album extends Genre {

    private String release_date;
    private String cover_medium;
    private String cover_big;
    private String cover_xl;
    private String cover_small;
    private String title;

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getCover_medium() {
        return cover_medium;
    }

    public String getCover_big() {
        return cover_big;
    }

    public String getCover_xl() {
        return cover_xl;
    }

    public String getCover_small() {
        return cover_small;
    }
}
