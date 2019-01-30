package com.example.usrlocal.projetmobile;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class cardFragment extends Fragment {

    private int idImage;

    private TextView textCard = null;
    private ImageView imageCard = null;

    // List of cards.
    private static int[] listCardImages = {
            R.drawable.card_1,
            R.drawable.card_2,
            R.drawable.card_3,
            R.drawable.card_4,
            R.drawable.card_5,
            R.drawable.card_6,
            R.drawable.card_7,
            R.drawable.card_8,
    };

    public cardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card, container, false);

        this.textCard = (TextView) view.findViewById(R.id.text);
        this.imageCard = (ImageView) view.findViewById(R.id.imageCard);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        this.imageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((game) getActivity()).cardNotificationClicked(cardFragment.this);
            }
        });
    }

    /**
     * SetUp the card status and image id.
     * @param id_image : image id.
     */
    public void setUpCard(int id_image){
        this.idImage = id_image;
        this.textCard.setText(String.valueOf(id_image));
        this.imageCard.setImageResource(R.drawable.back_card);
    }

    /**
     * Set card as shown.
     */
    public void show(){
        // Show card image.
        this.imageCard.setImageResource(listCardImages[this.idImage]);
    }

    /**
     * Set card as hidden.
     */
    public void hide(){
        // Show back card image.
        this.imageCard.setImageResource(R.drawable.back_card);
    }

    /**
     * Set card as found.
     */
    public void setFind(){
        // Click disable on card.
        this.imageCard.setEnabled(false);
        // put gray filter on the card image.
        this.imageCard.setImageAlpha(63);
    }

    /**
     * Get card image id.
     * @return int : image id.
     */
    public int getIdImage() {
        return this.idImage;
    }
}
