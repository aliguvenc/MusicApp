package aliguvenc.musicapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import aliguvenc.musicapp.helper.Communication;
import aliguvenc.musicapp.helper.MusicApplication;
import aliguvenc.musicapp.R;
import aliguvenc.musicapp.databinding.ArtistRowLayoutBinding;
import aliguvenc.musicapp.http.Genre;
import aliguvenc.musicapp.viewmodel.ArtistRowViewModel;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ViewHolder> {

    private static Communication.Item itemClickListener;
    private List<Genre> artists;

    public ArtistsAdapter(Communication.Item itemClickListener) {
        ArtistsAdapter.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArtistRowLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(MusicApplication.getINSTANCE()),
                R.layout.artist_row_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(artists.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public void setArtists(List<Genre> artists) {
        this.artists = new ArrayList<>();
        this.artists = artists;
        notifyItemRangeInserted(0, artists.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ArtistRowLayoutBinding binding;

        public ViewHolder(ArtistRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Genre genre) {
            binding.setArtistRowViewModel(new ArtistRowViewModel(genre, itemClickListener));
        }
    }
}
