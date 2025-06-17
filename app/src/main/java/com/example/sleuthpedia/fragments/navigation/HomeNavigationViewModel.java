package com.example.sleuthpedia.fragments.navigation;

import android.os.Parcelable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

public class HomeNavigationViewModel extends ViewModel {

    public HashMap<Class<? extends Fragment>, Parcelable> scrollViewStates = new HashMap<>();
    public HashMap<Class<? extends Fragment>, String> searchBarStates = new HashMap<>();

}
