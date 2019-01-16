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

    private int pairesFound;
    private ArrayList<cardFragment> listCards = null;
    private ArrayList<cardFragment> listShownCards = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pairesFound = 0;
        listCards = new ArrayList<>();
        listShownCards = new ArrayList<>();

        Intent intent = getIntent();
        if(intent != null) {
            createCards(intent.getIntExtra("taille", 0));
        }
        else{
            Toast.makeText(this, "Oups tout ne s'est pas passé comme prévu, veuillez re-sélectionner le type de jeu.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpCards();
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

    private void setUpCards() {

        List<Integer> listImageId = new ArrayList<>();
        for(int i=0; i<listCards.size()/2; i++){
            listImageId.add(i);
            listImageId.add(i);
        }

        Collections.shuffle(listImageId);

        for(int i=0; i<listCards.size(); i++){
            listCards.get(i).setUpCard(listImageId.get(i));
        }
    }

    public void cardNotificationShown(cardFragment card){

        listShownCards.add(card);

        if(listShownCards.size()==2){

            cardFragment card0 = listShownCards.get(0);
            cardFragment card1 = listShownCards.get(1);

            if(card0.getIdImage() == card1.getIdImage()){

                card0.setFind();
                card1.setFind();
                listShownCards.clear();

                pairesFound++;
                if(pairesFound == listCards.size()/2){
                    gameFinished();
                }
            }
            else{
                card0.setIncorrect();
                card1.setIncorrect();
            }
        }

        if(listShownCards.size()==3){

            //TODO : Select an already selected card
            // See cardfragment cardNotification
            
            cardFragment card0 = listShownCards.get(0);
            cardFragment card1 = listShownCards.get(1);

            Toast.makeText(this, "same", Toast.LENGTH_LONG);
            card0.hide();
            card1.hide();
            listShownCards.remove(card0);
            listShownCards.remove(card1);

        }
    }

    public void gameFinished(){
        Toast.makeText(this, "Bravo partie terminée", Toast.LENGTH_LONG).show();
    }
}
