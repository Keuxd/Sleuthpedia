package com.example.sleuthpedia.fragments.navigation.listfragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.sleuthpedia.DatabaseHelper;
import com.example.sleuthpedia.GD;
import com.example.sleuthpedia.R;
import com.example.sleuthpedia.dscs.SupportSkill;
import com.example.sleuthpedia.fragments.navigation.recyclerviewadapter.MovesRecyclerViewAdapter;
import com.example.sleuthpedia.fragments.navigation.recyclerviewadapter.SupportSkillRecyclerViewAdapter;

import java.util.List;

public class SupportSkillListFragment extends BaseListFragment<SupportSkill>{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(adapter == null) {
            adapter = new SupportSkillRecyclerViewAdapter(R.layout.navigation_support_skill_row, allElements, supportSkill -> {
               GD.print("SUPPORT SKILLS GOES BRRRRR -> " + supportSkill.getName());
            });
        }
    }

    @Override
    protected List<SupportSkill> initAllElements() {
        return allElements == null ? DatabaseHelper.getInstance().getAllSupportSkills() : allElements;
    }
}
