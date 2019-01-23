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

    private boolean isShown;
    private boolean isFound;
    private int idImage;

    private TextView textCard = null;
    private ImageView imageCard = null;

    private static int[] listCardImages = {
            R.drawable.card_1,
            R.drawable.card_2,
            R.drawable.card_3,
            R.drawable.card_4,
            R.drawable.card_5,
            R.drawable.card_6,
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

    public void setUpCard(int id_image){
        this.isFound = false;
        this.isShown = false;
        this.idImage = id_image;

        this.textCard.setText(String.valueOf(id_image));
        this.imageCard.setImageResource(R.drawable.back_card);

        /*int id_image = this.getResources().getIdentifier(image_name , "drawable", getActivity().getPackageName());
        ImageView image = (ImageView) getView().findViewById(R.id.image);
        image.setImageResource(id_image);*/
    }

    public void show(){
        this.isShown = true;
        this.imageCard.setImageResource(listCardImages[this.idImage]);
    }

    public void hide(){
        this.isShown = false;
        this.imageCard.setImageResource(R.drawable.back_card);
    }

    public void setIncorrect(){

    }

    public void setFind(){
        this.isFound = true;
        this.imageCard.setEnabled(false);
        this.imageCard.setImageAlpha(63);
    }

    public int getIdImage() {
        return this.idImage;
    }
}
