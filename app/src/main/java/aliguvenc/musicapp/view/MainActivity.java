package aliguvenc.musicapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import aliguvenc.musicapp.Communication;
import aliguvenc.musicapp.R;
import aliguvenc.musicapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Communication.Item {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setGenreFragment();
    }

    private void setGenreFragment() {
        GenreFragment genreFragment = new GenreFragment();
        genreFragment.setClickListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainer.getId(), genreFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(Fragment fragment) {
        ((BaseFragment) fragment).setClickListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainer.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }
}
