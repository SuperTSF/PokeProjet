package com.example.pokeprojet;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PokeListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] pokeName;
    private final String[] pokeID;

    private String[] spriteUrls;


    public PokeListAdapter(Activity context, String[] pokeName, String[] subtitle, String[] spriteUrls) {

        super(context, R.layout.customlistview, pokeName);

        this.context=context;
        this.pokeName =pokeName;
        this.pokeID =subtitle;
        this.spriteUrls=spriteUrls;

    }


    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.customlistview, parent,false);

        TextView name = (TextView) rowView.findViewById(R.id.pokemonNameTextView);
        ImageView sprite = (ImageView) rowView.findViewById(R.id.pokemonImageView);
        TextView id = (TextView) rowView.findViewById(R.id.pokemonNationalDex);

        name.setText(pokeName[position]);

        String spriteUrl = spriteUrls[position];
        Picasso.get().load(spriteUrl).into(sprite);

        id.setText(pokeID[position]);

        return rowView;

    };

}

