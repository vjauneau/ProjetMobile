package com.example.usrlocal.projetmobile;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
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

    public cardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card, container, false);

        textCard = (TextView) view.findViewById(R.id.text);
        imageCard = (ImageView) view.findViewById(R.id.imageCard);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        imageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isShown){
                    show();
                    ((game) getActivity()).cardNotificationShown(cardFragment.this);
                }
            }
        });
    }

    public void setUpCard(int id_image){
        isFound = false;
        isShown = false;
        idImage = id_image;

        textCard.setText(String.valueOf(id_image));
        imageCard.setImageResource(R.drawable.back_card);

        /*int id_image = this.getResources().getIdentifier(image_name , "drawable", getActivity().getPackageName());
        ImageView image = (ImageView) getView().findViewById(R.id.image);
        image.setImageResource(id_image);*/
    }

    public void show(){
        isShown = true;
        imageCard.setImageResource(R.drawable.lapin_assassin);
    }

    public void hide(){
        isShown = false;
        imageCard.setImageResource(R.drawable.back_card);
        imageCard.setBackgroundColor(Color.WHITE);
    }

    public void setIncorrect(){
        imageCard.setBackgroundColor(Color.RED);
    }

    public void setFind(){
        isFound = true;
        imageCard.setEnabled(false);
        imageCard.setBackgroundColor(Color.GREEN);
    }

    public int getIdImage() {
        return idImage;
    }
}
