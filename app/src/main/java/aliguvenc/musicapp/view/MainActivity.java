package aliguvenc.musicapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import java.util.Arrays;

import aliguvenc.musicapp.R;
import aliguvenc.musicapp.databinding.ActivityMainBinding;
import aliguvenc.musicapp.helper.Communication;
import aliguvenc.musicapp.helper.MediaHelper;

public class MainActivity extends AppCompatActivity implements Communication.Item {

    private ActivityMainBinding binding;
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initToolbar();
        GenreFragment genreFragment = new GenreFragment();
        genreFragment.setArguments(args(GenreFragment.TAG));
        initFragment(genreFragment);
    }

    private void initToolbar() {
        setSupportActionBar(binding.mainToolbar);
        setTitle(getString(R.string.explore));
        binding.navigationView.setOnNavigationItemSelectedListener(listener);
    }

    @Override
    public void onClick(Fragment fragment) {
        initFragment(fragment);
    }

    private void initFragment(Fragment fragment) {
        ((BaseFragment) fragment).setClickListener(this);
        String tag = fragment.getArguments().getString(TAG);
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainer.getId(), fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            showToolbar();
            switch (item.getItemId()) {
                case R.id.explore:
                    replaceFragment(GenreFragment.TAG);
                    setToolbarTitle(R.string.explore);
                    break;
                case R.id.favourites:
                    if (getSupportFragmentManager().findFragmentByTag(FavoritesFragment.TAG) == null) {
                        FavoritesFragment favoritesFragment = new FavoritesFragment();
                        favoritesFragment.setArguments(args(FavoritesFragment.TAG));
                        initFragment(favoritesFragment);
                    } else {
                        replaceFragment(FavoritesFragment.TAG);
                    }
                    setToolbarTitle(R.string.favourites);
                    break;
            }
            return true;
        }
    };

    private void replaceFragment(String fragmentTag) {
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainer.getId(), getSupportFragmentManager().findFragmentByTag(fragmentTag))
                .commit();
        MediaHelper.getINSTANCE().stopPlayers();
    }

    private String tags[] = {GenreFragment.TAG, FavoritesFragment.TAG};

    @Override
    public void onBackPressed() {
        int index = getSupportFragmentManager().getBackStackEntryCount()-1;
        FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(index);
        String tag = backEntry.getName();
        if (Arrays.asList(tags).contains(tag)) {
            binding.mainToolbar.setVisibility(View.VISIBLE);
            binding.mainToolbar.setTitle(tag);
            moveTaskToBack(true);
        } else {
            super.onBackPressed();
        }
    }

    private Bundle args(String tag) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, tag);
        return bundle;
    }

    public void hideToolbar() {
        binding.mainToolbar.setVisibility(View.GONE);
    }

    private void setToolbarTitle(int id) {
        binding.mainToolbar.setTitle(id);
    }

    private void showToolbar() {
        binding.mainToolbar.setVisibility(View.VISIBLE);
    }
}
