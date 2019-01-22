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

    public void cardNotificationClicked(cardFragment card){

        card.show();

        cardFragment card0;
        cardFragment card1;

        switch(listShownCards.size()) {

            // 0 card already shown.
            case 0:
                listShownCards.add(card);
                break;

            // 1 card already shown (check pair).
            case 1:
                card0 = listShownCards.get(0);

                // Check if the cards are the same.
                if(card.getIdImage() == card0.getIdImage()){

                    // Set cards found
                    card.setFind();
                    card0.setFind();
                    listShownCards.clear();

                    pairesFound++;

                    // Check if game is finished
                    if(pairesFound == listCards.size()/2){
                        gameFinished();
                    }
                }
                else{
                    card.setIncorrect();
                    card0.setIncorrect();

                    listShownCards.add(card);
                }

                break;

            // 2 cards already shown (wrong pair).
            case 2:

                // Hide cards and clear the list.
                for(cardFragment c : listShownCards){
                    c.hide();
                }
                listShownCards.clear();

                // If the card was already shown, re-show it.
                card.show();

                // Add the new card to the shown cards list.
                listShownCards.add(card);

                break;

            default:
                Toast.makeText(this, "Erreur", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void gameFinished(){
        Toast.makeText(this, "Bravo partie terminée", Toast.LENGTH_LONG).show();
    }
}
