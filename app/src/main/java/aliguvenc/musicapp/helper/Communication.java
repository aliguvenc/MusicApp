package aliguvenc.musicapp.helper;

import android.support.v4.app.Fragment;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class Communication {

    public interface DataListener<T> {
        void onDataLoad(T data);
    }

    public interface Item {
        void onClick(Fragment fragment);
    }
}
