package com.example.usrlocal.projetmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class TabHistoriqueFragment extends Fragment {

    private TextView gameSizeText = null;
    private View view;

    private ImageView difficultyIcon = null;
    private TextView player_1 = null;
    private TextView time_1 = null;
    private TextView player_2 = null;
    private TextView time_2 = null;
    private TextView player_3 = null;
    private TextView time_3 = null;
    private TextView player_4 = null;
    private TextView time_4 = null;
    private TextView player_5 = null;
    private TextView time_5 = null;

    private List<TextView> players;
    private List<TextView> times;

    public TabHistoriqueFragment() {
        // Required empty public constructor
    }
    /**
     * Create a TabStatisticsFragment with the size of the game as parameters.
     * @param _gameSize : number of cards of the game.
     * @return TabStatisticsFragment : TabStatistics fragment.
     */
    public static TabHistoriqueFragment newInstance(int _gameSize) {
        TabHistoriqueFragment myTabFragment = new TabHistoriqueFragment();

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
        view = inflater.inflate(R.layout.fragment_tab_historique, container, false);

        this.difficultyIcon = (ImageView) view.findViewById(R.id.difficultyIcon);

        this.players = new ArrayList<>();
        this.players.add((TextView) view.findViewById(R.id.pseudo_5));
        this.players.add((TextView) view.findViewById(R.id.pseudo_4));
        this.players.add((TextView) view.findViewById(R.id.pseudo_3));
        this.players.add((TextView) view.findViewById(R.id.pseudo_2));
        this.players.add((TextView) view.findViewById(R.id.pseudo_1));

        this.times = new ArrayList<>();
        this.times.add((TextView) view.findViewById(R.id.time_5));
        this.times.add((TextView) view.findViewById(R.id.time_4));
        this.times.add((TextView) view.findViewById(R.id.time_3));
        this.times.add((TextView) view.findViewById(R.id.time_2));
        this.times.add((TextView) view.findViewById(R.id.time_1));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

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

        displayScoreBoard(getGameSize());
    }

    /**
     * Display the score board.
     * @param gameSize : number of cards on the game.
     */
    private void displayScoreBoard(int gameSize){

        SharedPreferences generalPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        // Set score board.
        for(int i=1; i<=5; i++){
            int scoreTime = generalPreferences.getInt("time" + String.valueOf(i) + "_game" + gameSize, 10000);
            String player = generalPreferences.getString("player" + String.valueOf(i) + "_game" + gameSize, null);
            if(player != null){
                this.players.get(i-1).setText(player);
                this.times.get(i-1).setText(String.valueOf(scoreTime) + "s");
            }

        }
    }
}
