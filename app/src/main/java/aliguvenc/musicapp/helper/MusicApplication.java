package aliguvenc.musicapp.helper;

import android.app.Application;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class MusicApplication extends Application {

    private static MusicApplication INSTANCE;

    public static MusicApplication getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MusicApplication();
        }
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
