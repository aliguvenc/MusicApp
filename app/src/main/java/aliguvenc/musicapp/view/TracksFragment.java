package aliguvenc.musicapp.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aliguvenc.musicapp.helper.Communication;
import aliguvenc.musicapp.helper.MediaHelper;
import aliguvenc.musicapp.helper.MusicApplication;
import aliguvenc.musicapp.R;
import aliguvenc.musicapp.databinding.FragmentTracksBinding;
import aliguvenc.musicapp.http.Album;
import aliguvenc.musicapp.http.TrackResponse;
import aliguvenc.musicapp.view.adapter.TracksAdapter;
import aliguvenc.musicapp.viewmodel.TrackViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TracksFragment extends BaseFragment implements Communication.DataListener<TrackResponse> {


    private FragmentTracksBinding binding;

    public TracksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(MusicApplication.getINSTANCE()),
                R.layout.fragment_tracks, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity)getActivity()).hideToolbar();
        TrackViewModel viewModel = new TrackViewModel(this, getArguments());
        binding.setTrackViewModel(viewModel);
    }

    public static Fragment newInstance(Album album) {
        TracksFragment tracksFragment = new TracksFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("album", album);
        tracksFragment.setArguments(bundle);
        return tracksFragment;
    }

    @Override
    public void onDataLoad(TrackResponse trackResponse) {
        TracksAdapter adapter = new TracksAdapter();
        binding.tracksRecyclerView.setAdapter(adapter);
        adapter.setTracks(trackResponse.getData());
    }

    @Override
    public void onDetach() {
        MediaHelper.getINSTANCE().stopPlayers();
        super.onDetach();
    }
}
