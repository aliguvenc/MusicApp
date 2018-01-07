package aliguvenc.musicapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import aliguvenc.musicapp.helper.MediaHelper;
import aliguvenc.musicapp.helper.MusicApplication;
import aliguvenc.musicapp.R;
import aliguvenc.musicapp.databinding.TrackRowLayoutBinding;
import aliguvenc.musicapp.http.Track;

/**
 * Created by aliguvenc on 7.01.2018.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List<Track> tracks;
    private TrackRowLayoutBinding binding;
    private MediaPlayer player;
    private Track track;

    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(MusicApplication.getINSTANCE()),
                R.layout.track_row_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final FavoritesAdapter.ViewHolder holder, int position) {
        holder.bind(tracks.get(holder.getAdapterPosition()));
    }

    private void removeTrack(int adapterPosition) {
        tracks.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        MediaHelper.getINSTANCE().stopPlayers();
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

    class ViewHolder extends RecyclerView.ViewHolder {

        TrackRowLayoutBinding binding;
        MediaPlayer player;
        Track track;

        ViewHolder(TrackRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Track track) {
            this.track = track;
            binding.trackName.setText(String.format("%s - %s", track.getGenre().getName(), track.getTitle()));
            binding.playPause.setOnClickListener(clickListener);
            binding.like.setOnClickListener(clickListener);
            binding.like.setChecked(track.isLiked());
        }

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.like:
                        if (track.isLiked()) {
                            removeTrack(getAdapterPosition());
                        }
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
