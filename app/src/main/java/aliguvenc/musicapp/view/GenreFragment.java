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
import aliguvenc.musicapp.databinding.FragmentExploreBinding;
import aliguvenc.musicapp.http.Genre;
import aliguvenc.musicapp.http.GenreResponse;
import aliguvenc.musicapp.view.adapter.GenreViewModel;
import aliguvenc.musicapp.viewmodel.ExploreViewModel;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;


/**
 * A simple {@link Fragment} subclass.
 */
public class GenreFragment extends Fragment implements Communication.DataListener<GenreResponse>
        ,Communication.Item<Genre> {

    private FragmentExploreBinding binding;

    public GenreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ExploreViewModel viewModel = new ExploreViewModel(this);
        viewModel.getGenres();
        binding.exploreMusicRV.setItemAnimator(new SlideInLeftAnimator());
        binding.exploreMusicRV.setAdapter(new GenreViewModel(this));
    }

    @Override
    public void onDataLoad(GenreResponse genreResponse) {
        if (genreResponse != null && genreResponse.getData() != null) {
            ((GenreViewModel) binding.exploreMusicRV.getAdapter()).setGenres(genreResponse.getData());
        }
    }

    @Override
    public void onClick(Genre item) {

    }
}
