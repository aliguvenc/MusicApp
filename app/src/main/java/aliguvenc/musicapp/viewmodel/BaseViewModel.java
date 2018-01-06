package aliguvenc.musicapp.viewmodel;

import android.databinding.ObservableBoolean;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class BaseViewModel {

    private ObservableBoolean isDataLoading;

    public BaseViewModel(ObservableBoolean isDataLoading) {
        this.isDataLoading = isDataLoading;
    }

    protected void setLoaiding(boolean isLoading){
        isDataLoading.set(isLoading);
    }
}
