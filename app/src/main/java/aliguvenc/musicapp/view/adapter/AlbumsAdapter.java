package aliguvenc.musicapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import aliguvenc.musicapp.Communication;
import aliguvenc.musicapp.MusicApplication;
import aliguvenc.musicapp.R;
import aliguvenc.musicapp.databinding.AlbumRowLayoutBinding;
import aliguvenc.musicapp.http.Album;
import aliguvenc.musicapp.viewmodel.AlbumRowViewModel;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {

    private static Communication.Item itemClickListener;
    private List<Album> albums;

    public AlbumsAdapter(Communication.Item itemClickListener) {
        AlbumsAdapter.itemClickListener = itemClickListener;
    }

    @Override
    public AlbumsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AlbumRowLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(MusicApplication.getINSTANCE()),
                R.layout.album_row_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(AlbumsAdapter.ViewHolder holder, int position) {
        holder.bind(albums.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public void setAlbums(List<Album> albums) {
        this.albums = new ArrayList<>();
        this.albums = albums;
        notifyItemRangeInserted(0, albums.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        AlbumRowLayoutBinding binding;

        public ViewHolder(AlbumRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Album album) {
            binding.setAlbumRowViewModel(new AlbumRowViewModel(album, itemClickListener));
        }
    }
}
