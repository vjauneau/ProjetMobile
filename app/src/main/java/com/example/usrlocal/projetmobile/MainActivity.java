package com.example.usrlocal.projetmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {

    Button boutonGame8 = null;
    Button boutonGame12 = null;
    CheckBox cbEnregister = null;
    EditText etPseudo = null;
    TextView tvBjr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        boutonGame8 = (Button) findViewById(R.id.btnG8);
        boutonGame12 = (Button) findViewById(R.id.btnG12);
        cbEnregister = (CheckBox) findViewById(R.id.cbEnr);
        etPseudo = (EditText) findViewById(R.id.etPseudo);
        tvBjr = (TextView) findViewById(R.id.tvBjr);
        boutonGame8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGame(8);

            }
        });

        boutonGame12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGame(12);
            }
        });

        cbEnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbEnregister.isChecked()){
                    etPseudo.setVisibility(View.VISIBLE);
                }else {
                    etPseudo.setVisibility(View.INVISIBLE);
                }
            }
        });

        int taille = prefs.getInt("taille",-1);
        String pseudo = prefs.getString("PSEUDO",null);
        if(taille != -1 && pseudo != null) {
            Toast.makeText(getApplicationContext(), "Bonjour "+ pseudo, Toast.LENGTH_SHORT).show();
            tvBjr.setText("Bonjour : "+pseudo+". Choisissez votre jeu.");
            // launchGame(taille);
        }

    }

    protected void launchGame(int taille) {
        Intent intentGame = null;
        if (cbEnregister.isChecked()) {
            String pseudo = etPseudo.getText().toString();
            Toast.makeText(getApplicationContext(), pseudo, Toast.LENGTH_SHORT).show();
            if (pseudo.length() == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Veuillez entrer un pseudo pour l'enregistrement.");
                builder.setCancelable(false);
                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
            else {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("PSEUDO", pseudo);
                editor.putInt("taille", taille);
                editor.apply();
                Toast.makeText(getApplicationContext(), "game : " + taille, Toast.LENGTH_SHORT).show();
                intentGame = new Intent(MainActivity.this, game.class);
                intentGame.putExtra("taille", taille);
                startActivity(intentGame);
            }
        }
        else {
            intentGame = new Intent(MainActivity.this, game.class);
            intentGame.putExtra("taille", taille);
            startActivity(intentGame);
            Toast.makeText(getApplicationContext(), "game : " + taille, Toast.LENGTH_SHORT).show();
            }
        }
    }
