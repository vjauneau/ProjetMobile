package com.example.usrlocal.projetmobile;

import android.content.Context;
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
    private boolean isFind;
    private int idImage;

    private TextView textCard = null;

    public cardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    public void setCard(int id_image){
        isFind = false;
        isShown = false;
        idImage = id_image;

        textCard = (TextView) getView().findViewById(R.id.text);
        textCard.setText(String.valueOf(id_image));

        /*int id_image = this.getResources().getIdentifier(image_name , "drawable", getActivity().getPackageName());
        ImageView image = (ImageView) getView().findViewById(R.id.image);
        image.setImageResource(id_image);*/
    }
}
