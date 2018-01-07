package aliguvenc.musicapp.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import aliguvenc.musicapp.http.Track;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class TrackRowViewModel extends BaseObservable{

    private Track track;

    public TrackRowViewModel(Track track) {
        this.track = track;
    }

    @Bindable
    public String getTitle(){
        return track.getTitle();
    }
}
