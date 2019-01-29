package com.example.usrlocal.projetmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

    private Intent intentStat = null;
    private Intent intentTopBoard = null;

    private Button btnSimple = null;
    private Button btnMedium = null;
    private Button btnDifficult = null;
    private Button btnPlay = null;
    private List<Button> ListbtnDifficulty;

    private int difficulty = 0;

    private Button btnSavePseudo = null;
    private EditText etPseudo = null;

    private TextView tvBjr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
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

               /* case 0:
                    Toast.makeText(MenuActivity., "Veuillez choisir un niveau de difficulté", Toast.LENGTH_LONG).show();*/
            }
        }
        });

        btnSavePseudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //si l'ET n'est pas vide on récupère le pseudo.
                if (etPseudo.getText() != null) {
                    String pseudo = etPseudo.getText().toString();

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("PSEUDO", pseudo);
                    editor.apply();
                }

                updatePseudoDisplay();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        updatePseudoDisplay();
    }

    private void updatePseudoDisplay(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String pseudo = prefs.getString("PSEUDO",null);
        if(pseudo != null) {
            Toast.makeText(getApplicationContext(), "Bonjour "+ pseudo, Toast.LENGTH_SHORT).show();
            tvBjr.setText("Bonjour : " + pseudo);
        }
    }

    /**
     * Lors du lancement de la partie si un pseudo est tapé on le recupère sinon on set attribue la valeur guest
     * @param taille
     */
    protected void launchGame(int taille) {
        Intent intentGame = null;

        intentGame = new Intent(MenuActivity.this, game.class);
        intentGame.putExtra("taille",taille);
        startActivity(intentGame);
    }

    protected void selectYourDifficultyLevel(){
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
        switch (item.getItemId()) {

            case R.id.action_stats:
                intentStat = new Intent(MenuActivity.this, StatisticActivity.class);
                break;

            case R.id.action_topBoard:
                intentStat = new Intent(MenuActivity.this, HistoriqueActivity.class);
                break;
        }
        startActivity(intentStat);

        return super.onOptionsItemSelected(item);
    }
}


