package com.example.sleuthpedia.fragments.menu;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sleuthpedia.R;
import com.example.sleuthpedia.fragments.navigation.listfragment.DigimonListFragment;
import com.example.sleuthpedia.fragments.navigation.listfragment.MovesListFragment;
import com.example.sleuthpedia.fragments.navigation.HomeNavigationViewModel;
import com.example.sleuthpedia.fragments.navigation.listfragment.SupportSkillListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class HomeFragment extends Fragment {

    public BottomNavigationView navigation;
    public HomeNavigationViewModel viewModel;
    public EditText searchBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(HomeNavigationViewModel.class);
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchBar = view.findViewById(R.id.search);
        setupNavigation();
    }

    private void setupNavigation() {
        navigation = getView().findViewById(R.id.navigation);
        navigation.setOnApplyWindowInsetsListener(null);

        HashMap<Integer, Fragment> fragmentMap = new HashMap<>();
            fragmentMap.put(R.id.navigation_digimon, new DigimonListFragment());
            fragmentMap.put(R.id.navigation_moves, new MovesListFragment());
            fragmentMap.put(R.id.navigation_skills, new SupportSkillListFragment());

        navigation.setOnItemSelectedListener(item -> {
            Fragment fragmentSelected = fragmentMap.get(item.getItemId());
            if(fragmentSelected == null) return false;

            getChildFragmentManager().beginTransaction().replace(R.id.navigationFragmentContainer, fragmentSelected).commit();
            return true;
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        navigation.setSelectedItemId(navigation.getSelectedItemId());
    }

    public void addSearchBarTextWatcher(TextWatcher textWatcher) {
        searchBar.addTextChangedListener(textWatcher);
    }

    public void saveSearchBarState(Class<? extends Fragment> fragmentClass) {
        viewModel.searchBarStates.put(fragmentClass, searchBar.getText().toString());
    }

    public void loadSearchBarState(Class<? extends Fragment> fragmentClass) {
        searchBar.setText(viewModel.searchBarStates.get(fragmentClass));
    }

}
