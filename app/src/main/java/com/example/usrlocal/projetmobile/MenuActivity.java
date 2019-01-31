package com.example.usrlocal.projetmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private Button btnSimple = null;
    private Button btnMedium = null;
    private Button btnDifficult = null;
    private Button btnPlay = null;
    private List<Button> ListbtnDifficulty;

    private int difficulty;

    private Button btnSavePseudo = null;
    private EditText etPseudo = null;

    private TextView tvBjr = null;

    private SharedPreferences preferences;

    private MediaPlayer mpSoundEffect = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        if (toolbar != null)setSupportActionBar(toolbar);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ListbtnDifficulty = new ArrayList<>();

        btnSimple = (Button) findViewById(R.id.btnSimple);
        btnMedium = (Button) findViewById(R.id.btnMedium);
        btnDifficult = (Button) findViewById(R.id.btnDifficult);
        btnPlay = (Button) findViewById(R.id.button4);
        btnSavePseudo = (Button) findViewById(R.id.savePseudo);

        ListbtnDifficulty.add(btnSimple);
        ListbtnDifficulty.add(btnMedium);
        ListbtnDifficulty.add(btnDifficult);

        etPseudo = (EditText) findViewById(R.id.etPseudo);
        tvBjr = (TextView) findViewById(R.id.tvBjr);

        difficulty = 2;
        btnMedium.setSelected(true);
        selectYourDifficultyLevel();

        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSimple.setSelected(true);
                btnMedium.setSelected(false);
                btnDifficult.setSelected(false);
                selectYourDifficultyLevel();
                difficulty = 1;
            }
        });

        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSimple.setSelected(false);
                btnMedium.setSelected(true);
                btnDifficult.setSelected(false);
                selectYourDifficultyLevel();
                difficulty = 2;
            }
        });

        btnDifficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSimple.setSelected(false);
                btnMedium.setSelected(false);
                btnDifficult.setSelected(true);
                selectYourDifficultyLevel();
                difficulty = 3;
            }
        });

        // Launch the game according to the selected difficulty.
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            switch (difficulty) {
                case 1:
                    launchGame(8);
                    break;
                case 2:
                    launchGame(12);
                    break;
                case 3:
                    launchGame(16);
                    break;
            }
        }
        });

        // Save the player pseudo from the editText.
        btnSavePseudo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                if (etPseudo.getText() != null) {
                    String pseudo = etPseudo.getText().toString();
                    setPlayerPseudo(pseudo);
                }
                updatePseudoDisplay();
            }
        });
    }

    /**
     * Update the display of the pseudo.
     */
    @Override
    protected void onStart() {
        super.onStart();
        updatePseudoDisplay();
    }

    /**
     * Update the pseudo textView.
     */
    private void updatePseudoDisplay(){
        String pseudo = this.preferences.getString("PSEUDO",null);
        if(pseudo != null) tvBjr.setText("Bonjour " + pseudo);
    }

    /**
     * Set the user pseudo in shared preferences.
     */
    private void setPlayerPseudo(String pseudo){
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString("PSEUDO", pseudo);
        editor.apply();
    }

    /**
     * Lors du lancement de la partie si un pseudo est tapé on le recupère sinon on set attribue la valeur guest.
     * @param taille : number of cards in the game.
     */
    private void launchGame(int taille) {

        // Default player.
        if(this.preferences.getString("PSEUDO",null)==null) {
            setPlayerPseudo("Invité");
        }

        // Reset the existing Media Player.
        if (mpSoundEffect != null) {
            mpSoundEffect.reset();
            mpSoundEffect.release();
        }
        // Change the sound effect and play it.
        mpSoundEffect = MediaPlayer.create(this, R.raw.entry_02);
        mpSoundEffect.start();

        Intent intentGame = null;

        intentGame = new Intent(MenuActivity.this, game.class);
        intentGame.putExtra("taille", taille);
        startActivity(intentGame);
        this.finish();
    }

    /**
     * Set the style of the selected difficulty level button.
     */
    private void selectYourDifficultyLevel(){
        for(Button b : ListbtnDifficulty){
            if(b.isSelected())b.setTextColor(Color.RED);
            else b.setTextColor(Color.BLACK);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = null;

        switch (item.getItemId()) {

            case R.id.action_stats:
                intent = new Intent(MenuActivity.this, StatisticActivity.class);
                break;

            case R.id.action_topBoard:
                intent = new Intent(MenuActivity.this, HistoriqueActivity.class);
                break;
        }

        startActivity(intent);
        this.finish();

        return super.onOptionsItemSelected(item);
    }
}


