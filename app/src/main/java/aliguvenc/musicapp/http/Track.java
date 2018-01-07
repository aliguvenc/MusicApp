package aliguvenc.musicapp.http;

/**
 * Created by aliguvenc on 5.01.2018.
 */

public class Track {

    private String id;
    private String title;
    private String preview;
    private boolean isPlaying;
    private boolean isLiked;
    private Genre artist;

    public Genre getGenre() {
        return artist;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
