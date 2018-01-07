package aliguvenc.musicapp.view;


import android.support.v4.app.Fragment;

import aliguvenc.musicapp.helper.Communication;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    private Communication.Item clickListener;

    public Communication.Item getClickListener() {
        return clickListener;
    }

    public void setClickListener(Communication.Item clickListener) {
        this.clickListener = clickListener;
    }
}
