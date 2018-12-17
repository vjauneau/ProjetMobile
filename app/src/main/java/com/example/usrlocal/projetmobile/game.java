package com.example.usrlocal.projetmobile;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class game extends AppCompatActivity {

    private int nbCards;
    private ArrayList<cardFragment> listCards = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        listCards = new ArrayList<>();

        Intent intent = getIntent();
        if(intent != null) {
            nbCards = intent.getIntExtra("taille", 0);
            createCards(nbCards);
        }
        else{
            Toast.makeText(this, "Oups tout ne s'est pas passé comme prévu, veuillez re-sélectionner le type de jeu.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setCards();
    }


    private void createCards(int nbCards) {
        int rowCard = 1;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        for(int i=0; i<nbCards ; i++) {
            cardFragment card = new cardFragment();
            listCards.add(card);
            int layoutId = getResources().getIdentifier("layoutCardRow" + String.valueOf(rowCard), "id", getPackageName());
            ft.add(layoutId, card);
            if((i+1)%4==0)rowCard ++;
        }
        ft.commit();
    }

    private void setCards() {

        List<Integer> listImageId = new ArrayList<>();
        for(int i=0; i<listCards.size()/2; i++){
            listImageId.add(i);
            listImageId.add(i);
        }

        Collections.shuffle(listImageId);

        for(int i=0; i<listCards.size(); i++){
            listCards.get(i).setCard(listImageId.get(i));
        }
    }
}
