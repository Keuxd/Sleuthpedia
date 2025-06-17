package com.example.sleuthpedia.fragments.navigation.recyclerviewadapter;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.example.sleuthpedia.R;
import com.example.sleuthpedia.dscs.SupportSkill;

import java.util.List;

public class SupportSkillRecyclerViewAdapter extends BaseRecyclerViewAdapter<SupportSkill>{

    public SupportSkillRecyclerViewAdapter(int navigationRowId, List<SupportSkill> supportSkills, OnItemClickListener<SupportSkill> listener) {
        super(navigationRowId, supportSkills, listener);
    }

    @Override
    public void updateData(List<SupportSkill> newSupportSkills) {
        if(newSupportSkills.isEmpty()) {
            newSupportSkills.add(new SupportSkill(-1, "No results found", "Try changing your search!"));
        }

        super.updateData(newSupportSkills);
    }

    @Override
    public void bind(View itemView, SupportSkill supportSkill, OnItemClickListener<SupportSkill> listener) {
        TextView supportSkillName = itemView.findViewById(R.id.support_skill_name);
        TextView supportSkillDescription = itemView.findViewById(R.id.support_skill_description);

        supportSkillName.setText(supportSkill.getName());
        makeTextViewUnderlined(supportSkillName);

        supportSkillDescription.setText(supportSkill.getDescription());
    }

    public void makeTextViewUnderlined(TextView textView) {
        SpannableString content = new SpannableString(textView.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);
    }
}
