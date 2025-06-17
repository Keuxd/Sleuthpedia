package com.example.sleuthpedia.fragments.navigation.listfragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.sleuthpedia.DatabaseHelper;
import com.example.sleuthpedia.GD;
import com.example.sleuthpedia.R;
import com.example.sleuthpedia.dscs.Move;
import com.example.sleuthpedia.fragments.navigation.recyclerviewadapter.MovesRecyclerViewAdapter;

import java.util.List;

public class MovesListFragment extends BaseListFragment<Move> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(adapter == null) {
            adapter = new MovesRecyclerViewAdapter(R.layout.navigation_moves_row, allElements, move ->{
                GD.print("MOVE GOESBRRRR -> " + move.getName());
            });
        }
    }

    @Override
    protected List<Move> initAllElements() {
        return allElements == null ? DatabaseHelper.getInstance().getAllMoves() : allElements;
    }
}
