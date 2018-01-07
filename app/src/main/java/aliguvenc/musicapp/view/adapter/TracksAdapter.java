package aliguvenc.musicapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aliguvenc.musicapp.MusicApplication;
import aliguvenc.musicapp.R;
import aliguvenc.musicapp.databinding.TrackRowLayoutBinding;
import aliguvenc.musicapp.http.Track;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {

    private List<Track> tracks;

    @Override
    public TracksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TrackRowLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(MusicApplication.getINSTANCE()),
                R.layout.track_row_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TracksAdapter.ViewHolder holder, int position) {
        holder.bind(tracks.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = new ArrayList<>();
        this.tracks = tracks;
        notifyItemRangeInserted(0, tracks.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TrackRowLayoutBinding binding;
        Track track;
        MediaPlayer player;

        ViewHolder(TrackRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Track track) {
            this.track = track;
            binding.trackName.setText(track.getTitle());
            binding.playPause.setOnClickListener(clickListener);
            binding.like.setOnClickListener(clickListener);
        }

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.like:
                        if (track.isLiked()) {
                            binding.like.setChecked(false);
                            track.setLiked(false);
                        } else {
                            binding.like.setChecked(true);
                            track.setLiked(true);
                        }
                        break;
                    case R.id.playPause:
                        if (player == null) {
                            player = new MediaPlayer();
                            try {
                                binding.playPause.setImageResource(R.drawable.pause);
                                track.setPlaying(true);
                                player.setDataSource(track.getPreview());
                                player.prepare();
                                player.start();
                                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                        binding.playPause.setImageResource(R.drawable.play);
                                        track.setPlaying(false);
                                    }
                                });
                            } catch (Exception e) {
                                Toast.makeText(MusicApplication.getINSTANCE(), "Şarkı çalınırken bir hata oluştu.", Toast.LENGTH_SHORT).show();
                            }
                        }else if (player.isPlaying()){
                            player.pause();
                            binding.playPause.setImageResource(R.drawable.play);
                            track.setPlaying(false);
                        }else {
                            player.start();
                            binding.playPause.setImageResource(R.drawable.pause);
                        }
                        break;
                }
            }
        };
    }
}
