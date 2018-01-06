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
import aliguvenc.musicapp.databinding.GenresRowLayoutBinding;
import aliguvenc.musicapp.http.Genre;
import aliguvenc.musicapp.viewmodel.GenreRowViewModel;

/**
 * Created by aliguvenc on 6.01.2018.
 */

public class GenreViewModel extends RecyclerView.Adapter<GenreViewModel.ViewHolder> {

    private List<Genre> genres;
    private static int genresSize;
    private static Communication.Item<Genre> itemClickListener;

    public GenreViewModel(Communication.Item<Genre> itemClickListener) {
        GenreViewModel.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GenresRowLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(MusicApplication.getINSTANCE()),
                R.layout.genres_row_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(genres.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        genresSize = genres == null ? 0 : genres.size();
        return genresSize;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = new ArrayList<>();
        this.genres = genres;
        notifyItemRangeInserted(0, genres.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        GenresRowLayoutBinding binding;

        ViewHolder(GenresRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Genre genre) {
            binding.setGenreRovVm(new GenreRowViewModel(genre));
            binding.setItemClickListener(itemClickListener);
        }
    }
}
