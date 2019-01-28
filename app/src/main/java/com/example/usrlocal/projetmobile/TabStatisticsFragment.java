package com.example.usrlocal.projetmobile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class TabStatisticsFragment extends Fragment {

    private int gameSize = 0;
    private TextView gameSizeText = null;
    private View view;

    public TabStatisticsFragment() {
        // Required empty public constructor
    }

    /**
     * Create a TabStatisticsFragment with the size of the game as parameters.
     * @param _gameSize : number of cards of the game.
     * @return TabStatisticsFragment : TabStatistics fragment.
     */
    public static TabStatisticsFragment newInstance(int _gameSize) {
        TabStatisticsFragment myTabFragment = new TabStatisticsFragment();

        Bundle args = new Bundle(1);
        args.putInt("gameSize", _gameSize);
        myTabFragment.setArguments(args);

        return myTabFragment;
    }

    /**
     * Get the size of the game.
     * @return int : size of the game.
     */
    public int getGameSize() {
        return getArguments().getInt("gameSize", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_statistics, container, false);
        this.gameSizeText = (TextView) view.findViewById(R.id.gameSize);
        this.gameSizeText.setText(String.valueOf(getGameSize()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
