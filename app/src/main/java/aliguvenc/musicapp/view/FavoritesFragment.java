package aliguvenc.musicapp.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aliguvenc.musicapp.R;
import aliguvenc.musicapp.databinding.FragmentFavoritesBinding;
import aliguvenc.musicapp.helper.MediaHelper;
import aliguvenc.musicapp.helper.PreferencesManager;
import aliguvenc.musicapp.view.adapter.FavoritesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends BaseFragment {

    public static final String TAG = "Favoriler";
    private FragmentFavoritesBinding binding;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FavoritesAdapter adapter = new FavoritesAdapter();
        binding.favoritesRV.setAdapter(adapter);
        adapter.setTracks(PreferencesManager.getINSTANCE().getFavoriteList());
    }

    @Override
    public void onDestroy() {
        MediaHelper.getINSTANCE().stopPlayers();
        super.onDestroy();
    }
}
