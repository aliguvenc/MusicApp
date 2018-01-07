package aliguvenc.musicapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import aliguvenc.musicapp.MediaHelper;
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
                        MediaHelper.getINSTANCE().onLikeButtonClicked(track, binding.like);
                        break;
                    case R.id.playPause:
                        if (player == null) {
                            player = new MediaPlayer();
                            MediaHelper.getINSTANCE().onPlayButtonClicked(track, binding.playPause, player, false);
                        } else
                            MediaHelper.getINSTANCE().onPlayButtonClicked(track, binding.playPause, player, true);
                        break;
                }
            }
        };
    }
}
