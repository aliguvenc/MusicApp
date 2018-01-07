package aliguvenc.musicapp.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aliguvenc.musicapp.helper.Communication;
import aliguvenc.musicapp.R;
import aliguvenc.musicapp.databinding.FragmentExploreBinding;
import aliguvenc.musicapp.http.GenreResponse;
import aliguvenc.musicapp.view.adapter.GenreAdapter;
import aliguvenc.musicapp.viewmodel.GenreViewModel;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;


/**
 * A simple {@link Fragment} subclass.
 */
public class GenreFragment extends BaseFragment implements Communication.DataListener<GenreResponse> {

    private FragmentExploreBinding binding;
    public static final String TAG = "Keşfet";

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
        super.onViewCreated(view,savedInstanceState);
        new GenreViewModel(this);
        binding.exploreMusicRV.setItemAnimator(new SlideInLeftAnimator());
        binding.exploreMusicRV.setAdapter(new GenreAdapter(getClickListener()));
    }

    @Override
    public void onDataLoad(GenreResponse genreResponse) {
        if (genreResponse != null && genreResponse.getData() != null) {
            ((GenreAdapter) binding.exploreMusicRV.getAdapter()).setGenres(genreResponse.getData());
        }
    }
}
