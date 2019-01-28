package com.example.usrlocal.projetmobile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TabStatisticsFragment extends Fragment {

    private int gameSize;

    public TabStatisticsFragment() {
        // Required empty public constructor
    }

    public static TabStatisticsFragment newInstance(int _gameSize) {
        TabStatisticsFragment myTabFragment = new TabStatisticsFragment();

        Bundle args = new Bundle();
        args.putInt("gameSize", _gameSize);
        myTabFragment.setArguments(args);

        return myTabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.gameSize = getArguments().getInt("someInt", 8);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_statistics, container, false);
    }
}
