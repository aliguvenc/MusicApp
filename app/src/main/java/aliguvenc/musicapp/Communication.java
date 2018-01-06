package aliguvenc.musicapp;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class Communication {

    public interface DataListener<T> {
        void onDataLoad(T data);
    }

    public interface Item<T> {
        void onClick(T item);
    }
}
