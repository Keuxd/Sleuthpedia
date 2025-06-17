package com.example.sleuthpedia.fragments.navigation.recyclerviewadapter;

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.sleuthpedia.R;
import com.example.sleuthpedia.dscs.Digimon;
import com.example.sleuthpedia.enums.Attribute;
import com.example.sleuthpedia.enums.DigimonType;

import java.util.List;

public class DigimonRecyclerViewAdapter extends BaseRecyclerViewAdapter<Digimon> {

    public DigimonRecyclerViewAdapter(int navigationRowId, List<Digimon> digimons, OnItemClickListener<Digimon> listener) {
        super(navigationRowId, digimons, listener);
    }

    @Override
    public void updateData(List<Digimon> newDigimons) {
        if(newDigimons.isEmpty()) {
            newDigimons.add(new Digimon(0, "No results found", DigimonType.FREE, Attribute.NEUTRAL));
        }

        super.updateData(newDigimons);
    }

    @Override
    public void bind(View itemView, Digimon digimon, BaseRecyclerViewAdapter.OnItemClickListener<Digimon> listener) {
        TextView digimonId = itemView.findViewById(R.id.digimonId);
        TextView digimonName = itemView.findViewById(R.id.digimonName);
        ImageView digimonIcon = itemView.findViewById(R.id.digimonIcon);
        ImageView digimonAttribute = itemView.findViewById(R.id.digimonAttribute);

        digimonId.setText(String.format("#%03d", digimon.getId()));
        digimonName.setText(digimon.getName());

        loadAssetsImageInto("digi" + digimon.getId(), "digimons", digimonIcon);
        loadAssetsImageInto(digimon.getAttribute().toString().toLowerCase(), "attributes", digimonAttribute);

        itemView.setOnClickListener(view -> listener.onItemClick(digimon));
    }
}
