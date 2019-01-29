package com.example.usrlocal.projetmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class TabStatisticsFragment extends Fragment {

    private int gameSize = 0;
    private TextView gameSizeText = null;
    private View view;

    private ImageView difficultyIcon = null;
    private TextView pseudoText = null;
    private TextView bestTimeText = null;
    private TextView nbGamesText = null;
    private TextView perCentWinText = null;

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
        //this.gameSizeText.setText(String.valueOf(getGameSize()));

        this.difficultyIcon = (ImageView) view.findViewById(R.id.difficultyIcon);
        this.pseudoText = (TextView) view.findViewById(R.id.pseudo);
        this.bestTimeText = (TextView) view.findViewById(R.id.bestTime);
        this.nbGamesText = (TextView) view.findViewById(R.id.gamePlayed);
        this.perCentWinText = (TextView) view.findViewById(R.id.perCentWin);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        // Set difficulty icon.
        switch (getGameSize()) {
            case 8:
                this.difficultyIcon.setImageResource(R.drawable.easy);
                break;
            case 12:
                this.difficultyIcon.setImageResource(R.drawable.medium);
                break;
            case 16:
                this.difficultyIcon.setImageResource(R.drawable.hard);
                break;
        }

        // Set player name.
        this.pseudoText.setText(preferences.getString("PSEUDO",null));

        
    }
}
