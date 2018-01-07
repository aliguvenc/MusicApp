package aliguvenc.musicapp.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aliguvenc.musicapp.Communication;
import aliguvenc.musicapp.MusicApplication;
import aliguvenc.musicapp.R;
import aliguvenc.musicapp.databinding.FragmentAlbumBinding;
import aliguvenc.musicapp.http.AlbumResponse;
import aliguvenc.musicapp.http.Genre;
import aliguvenc.musicapp.view.adapter.AlbumsAdapter;
import aliguvenc.musicapp.viewmodel.AlbumViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends BaseFragment implements Communication.DataListener<AlbumResponse> {

    private FragmentAlbumBinding binding;

    public AlbumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(MusicApplication.getINSTANCE()),
                R.layout.fragment_album, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AlbumViewModel viewModel = new AlbumViewModel(this);
        String id = ((Genre) getArguments().getSerializable("genre")).getId();
        viewModel.getAlbums(id);
        binding.setAlbumViewModel(viewModel);
    }

    public static Fragment newInstance(Genre genre) {
        AlbumFragment albumFragment = new AlbumFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("genre", genre);
        albumFragment.setArguments(bundle);
        return albumFragment;
    }

    @Override
    public void onDataLoad(AlbumResponse albums) {
        AlbumsAdapter adapter = new AlbumsAdapter(getClickListener());
        binding.albumsRecyclerView.setAdapter(adapter);
        adapter.setArtists(albums.getData());
    }
}
