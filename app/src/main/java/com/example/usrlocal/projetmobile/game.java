package com.example.usrlocal.projetmobile;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class game extends AppCompatActivity {

    private int pairesFound;
    private Boolean cardsSetUp = false;
    private ImageView choiceStatusImage = null;
    private TextView choiceStatusText = null;
    private ArrayList<cardFragment> listCards = null;
    private ArrayList<cardFragment> listShownCards = null;

    private MediaPlayer mpSoundEffect = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.pairesFound = 0;
        this.choiceStatusImage = (ImageView) findViewById(R.id.choiceStatusImage);
        this.choiceStatusText = (TextView) findViewById(R.id.choiceStatusText);
        this.listCards = new ArrayList<>();
        this.listShownCards = new ArrayList<>();

        // Get the player pseudo from shar.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String pseudo = preferences.getString("PSEUDO",null);

        // Display the player pseudo.
        TextView pseudoTextView = (TextView)findViewById(R.id.userName);
        if(pseudo != null) pseudoTextView.setText("Joueur : " + pseudo);
        else pseudoTextView.setText("Joueur : Invité");

        // Get the game size and create the cards.
        Intent intent = getIntent();
        if(intent != null) createCards(intent.getIntExtra("taille", 0));
        else Toast.makeText(this, "Oups tout ne s'est pas passé comme prévu, veuillez re-sélectionner le type de jeu.", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Assign an image to each cards.
        if(!cardsSetUp)setUpCards();
    }

    /**
     * Create all card fragment needed for the game.
     * @param nbCards : number of card of the game
     */
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

    /**
     * Assign an image to each cards.
     */
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

        cardsSetUp = true;
    }

    /**
     * Update the game status when a card is clicked.
     * @param card : cardFragment clicked
     */
    public void cardNotificationClicked(cardFragment card){

        card.show();

        cardFragment card0;
        cardFragment card1;

        switch(listShownCards.size()) {

            // 0 card already shown.
            case 0:
                listShownCards.add(card);
                choiceSearch();
                break;

            // 1 card already shown (check pair).
            case 1:

                card0 = listShownCards.get(0);

                if(card != card0){
                    // Check if the cards are the same.
                    if(card.getIdImage() == card0.getIdImage()){

                        // Set cards found
                        card.setFind();
                        card0.setFind();
                        listShownCards.clear();

                        pairesFound++;

                        // Check if game is finished
                        if(pairesFound == listCards.size()/2){
                            gameWin();
                        }
                        else choiceSuccess();
                    }
                    else{
                        listShownCards.add(card);
                        choiceFail();
                    }
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

                choiceSearch();

                break;

            default:
                Toast.makeText(this, "Erreur", Toast.LENGTH_LONG).show();
                break;
        }
    }

    /**
     * Show win modal and save game on statistics when the game is won.
     */
    private void gameWin(){

        // Create winner dialog.
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.end_game_modal);

        // Set the custom dialog components - text, image and button.
        TextView text = (TextView) dialog.findViewById(R.id.textView);
        text.setText("Victoire !");

        ImageView image = (ImageView) dialog.findViewById(R.id.imageView);
        image.setImageResource(R.drawable.rabbid_success);

        Button dialogButton = (Button) dialog.findViewById(R.id.button);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGame = new Intent(game.this, MainActivity.class);
                startActivity(intentGame);
            }
        });
        dialog.show();

        // Play win sound.
        playSoundEffect(R.raw.win_sound);

        // Save statistics.

        // number of game won / lost
        // time of the game
        // best time
    }

    /**
     * Update the display if the choice isn't made.
     */
    private void choiceSearch(){
        // Change the rabbid image.
        this.choiceStatusImage.setImageResource(R.drawable.rabbid_search);
        this.choiceStatusText.setText("Cherche bien !");
    }

    /**
     * Update the display and play a sound if the choice is successful.
     */
    private void choiceSuccess(){
        // Play success sound.
        playSoundEffect(R.raw.wouhouh);

        // Change the rabbid image.
        this.choiceStatusImage.setImageResource(R.drawable.rabbid_success);
        this.choiceStatusText.setText("BRAVO !");
    }

    /**
     * Update the display and play a sound if the choice is wrong.
     */
    private void choiceFail(){
        // Play fail sound.
        playSoundEffect(R.raw.lauch);

        // Change the rabbid image.
        this.choiceStatusImage.setImageResource(R.drawable.rabbid_fail);
        this.choiceStatusText.setText("LOUPE !");
    }

    /**
     * Play a sound effect.
     * @param sound : sound effect to play
     */
    private void playSoundEffect(int sound){

        // Reset the existing Media Player.
        if (mpSoundEffect != null) {
            mpSoundEffect.reset();
            mpSoundEffect.release();
        }

        // Change the sound effect and play it.
        mpSoundEffect = MediaPlayer.create(this, sound);
        mpSoundEffect.start();
    }
}
