package net.middleland.mybluecar.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.middleand.mobile.rccar.R;

/**
 * A placeholder fragment containing Settings Application view.
 */
public class SettingFragment extends Fragment {

    public SettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_setting, container,
                false);
        return rootView;
    }
}
