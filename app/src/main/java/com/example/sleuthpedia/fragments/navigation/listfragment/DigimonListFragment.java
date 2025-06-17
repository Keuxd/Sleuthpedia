package com.example.sleuthpedia.fragments.navigation.listfragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.sleuthpedia.DatabaseHelper;
import com.example.sleuthpedia.R;
import com.example.sleuthpedia.dscs.Digimon;
import com.example.sleuthpedia.fragments.navigation.recyclerviewadapter.DigimonRecyclerViewAdapter;

import java.util.List;

public class DigimonListFragment extends BaseListFragment<Digimon> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (adapter == null) {
            adapter = new DigimonRecyclerViewAdapter(R.layout.navigation_digimon_row, allElements, digimon -> {
                Log.d("PRINT", "RECYCLER GOES BRRRR -> " + digimon.getName());
            });
        }
    }

    @Override
    protected List<Digimon> initAllElements() {
        return allElements == null ? DatabaseHelper.getInstance().getAllDigimons() : allElements;
    }
}
