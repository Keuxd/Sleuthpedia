package com.example.sleuthpedia.fragments.navigation.recyclerviewadapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sleuthpedia.R;
import com.example.sleuthpedia.dscs.Move;
import com.example.sleuthpedia.enums.Attribute;
import com.example.sleuthpedia.enums.MoveType;

import java.util.List;

public class MovesRecyclerViewAdapter extends BaseRecyclerViewAdapter<Move> {

    public MovesRecyclerViewAdapter(int navigationRowId, List<Move> moves, OnItemClickListener<Move> listener) {
        super(navigationRowId, moves, listener);
    }

    @Override
    public void updateData(List<Move> newMoves) {
        if (newMoves.isEmpty()) {
            newMoves.add(new Move(-1, "No results found", "Try changing your search!", Attribute.NEUTRAL, MoveType.DIRECT, 0,0,false));
        }

        super.updateData(newMoves);
    }

    @Override
    public void bind(View itemView, Move move, OnItemClickListener<Move> listener) {
        ImageView moveAttributeIcon = itemView.findViewById(R.id.moveAttributeIcon);
        TextView moveName = itemView.findViewById(R.id.moveName);
        TextView moveDescription = itemView.findViewById(R.id.moveDescription);
        TextView moveCostPower = itemView.findViewById(R.id.moveCostPower);

        loadAssetsImageInto(move.getAttribute().toString().toLowerCase(), "attributes", moveAttributeIcon);
        moveName.setText(move.getName());
        moveDescription.setText(move.getDescription());

        String formattedCostPower = String.format("SPcost: %d\nPower: %d", move.getSpCost(), move.getPower());
        moveCostPower.setText(formattedCostPower);

        itemView.setOnClickListener(view -> listener.onItemClick(move));
    }
}
