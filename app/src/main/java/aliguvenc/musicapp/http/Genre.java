package aliguvenc.musicapp.http;

import java.io.Serializable;

/**
 * Created by aliguvenc on 5.01.2018.
 */

public class Genre implements Serializable {

    private String id;
    private String name;
    private String picture;
    private String picture_small;
    private String picture_medium;
    private String picture_big;
    private String picture_xl;
    private String type;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getPicture_small() {
        return picture_small;
    }

    public String getPicture_medium() {
        return picture_medium;
    }

    public String getPicture_big() {
        return picture_big;
    }

    public String getPicture_xl() {
        return picture_xl;
    }

    public String getType() {
        return type;
    }
}
