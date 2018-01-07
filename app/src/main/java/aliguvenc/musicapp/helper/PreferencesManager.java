package aliguvenc.musicapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import aliguvenc.musicapp.http.Track;

/**
 * Created by aliguvenc on 7.01.2018.
 */

public class PreferencesManager {

    private static PreferencesManager INSTANCE;
    private static SharedPreferences preferences;
    private static final String PREFERENCE_NAME = "preference_name";
    private static final String FAVORITES = "favorites";
    private Gson gson;

    public static PreferencesManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesManager();
        }
        return INSTANCE;
    }

    private PreferencesManager() {
        preferences = MusicApplication.getINSTANCE().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void addToFavoriteList(Track track) {
        List<Track> trackList = getFavoriteList();
        trackList.add(track);
        saveList(trackList);
    }

    public void removeFromFavoriteList(Track track) {
        List<Track> trackList = getFavoriteList();
        for (Track track1 : trackList) {
            if (track.getId().equals(track1.getId())) {
                trackList.remove(track1);
                break;
            }
        }
        saveList(trackList);
    }

    private void saveList(List<Track> trackList) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(FAVORITES, gson.toJson(trackList));
        editor.apply();
    }

    public List<Track> getFavoriteList() {
        Type type = new TypeToken<List<Track>>() {
        }.getType();
        return gson.fromJson(preferences.getString(FAVORITES, null), type);
    }
}
