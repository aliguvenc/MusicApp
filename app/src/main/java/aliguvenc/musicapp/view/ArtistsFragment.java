package aliguvenc.musicapp.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aliguvenc.musicapp.Communication;
import aliguvenc.musicapp.R;
import aliguvenc.musicapp.databinding.FragmentArtistsBinding;
import aliguvenc.musicapp.http.Genre;
import aliguvenc.musicapp.http.GenreResponse;
import aliguvenc.musicapp.view.adapter.ArtistsAdapter;
import aliguvenc.musicapp.viewmodel.ArtistsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistsFragment extends BaseFragment implements Communication.DataListener<GenreResponse> {

    private FragmentArtistsBinding binding;


    public ArtistsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_artists, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArtistsViewModel viewModel = new ArtistsViewModel(this, getArguments());
        binding.setArtistsViewModel(viewModel);
    }

    public static Fragment newInstance(Genre genre) {
        ArtistsFragment artistsFragment = new ArtistsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("genre", genre);
        artistsFragment.setArguments(bundle);
        return artistsFragment;
    }

    @Override
    public void onDataLoad(GenreResponse genreResponse) {
        ArtistsAdapter adapter = new ArtistsAdapter(getClickListener());
        binding.artistsRecyclerView.setAdapter(adapter);
        adapter.setArtists(genreResponse.getData());
    }
}
