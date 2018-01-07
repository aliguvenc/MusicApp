package aliguvenc.musicapp.http;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import aliguvenc.musicapp.helper.MusicApplication;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public abstract class BaseCallback<T> implements Callback<T> {

    private ObservableBoolean isDataLoading;

    public BaseCallback(@Nullable ObservableBoolean isDataLoading) {
        this.isDataLoading = isDataLoading;
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        if (isDataLoading != null) {
            isDataLoading.set(false);
        }
        Toast.makeText(MusicApplication.getINSTANCE(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
