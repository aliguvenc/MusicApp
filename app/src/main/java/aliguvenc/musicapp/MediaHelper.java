package aliguvenc.musicapp;

import android.media.MediaPlayer;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import aliguvenc.musicapp.http.Track;

/**
 * Created by aliguvenc on 7.01.2018.
 */

public class MediaHelper {

    private static MediaHelper INSTANCE;

    public static MediaHelper getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MediaHelper();
        }
        return INSTANCE;
    }

    public void onPlayButtonClicked(final Track track, final ImageView playButton, MediaPlayer player,boolean isInitialised){
        if (!isInitialised) {
            try {
                playButton.setImageResource(R.drawable.pause);
                track.setPlaying(true);
                player.setDataSource(track.getPreview());
                player.prepare();
                player.start();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        playButton.setImageResource(R.drawable.play);
                        track.setPlaying(false);
                    }
                });
            } catch (Exception e) {
                Toast.makeText(MusicApplication.getINSTANCE(), "Şarkı çalınırken bir hata oluştu.", Toast.LENGTH_SHORT).show();
            }
        }else if (player.isPlaying()){
            player.pause();
            playButton.setImageResource(R.drawable.play);
            track.setPlaying(false);
        }else {
            player.start();
            playButton.setImageResource(R.drawable.pause);
        }
    }

    public void onLikeButtonClicked(Track track, CheckBox likeButton){
        if (track.isLiked()) {
            likeButton.setChecked(false);
            track.setLiked(false);
        } else {
            likeButton.setChecked(true);
            track.setLiked(true);
        }
    }
}
