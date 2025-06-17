package com.example.sleuthpedia.fragments.navigation.listfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sleuthpedia.R;
import com.example.sleuthpedia.SimpleTextWatcher;
import com.example.sleuthpedia.dscs.Filterable;
import com.example.sleuthpedia.fragments.menu.HomeFragment;
import com.example.sleuthpedia.fragments.navigation.recyclerviewadapter.BaseRecyclerViewAdapter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseListFragment<T extends Filterable> extends Fragment {

    protected List<T> allElements;
    protected RecyclerView recyclerView;

    protected BaseRecyclerViewAdapter<T> adapter;
    protected SimpleTextWatcher textWatcher;

    protected abstract List<T> initAllElements();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.allElements = initAllElements();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initSearchBarTextWatcher();
        getParent().loadSearchBarState(getClass());

        View view = inflater.inflate(R.layout.navigation_recycler_view_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.post(() -> recyclerView.getLayoutManager().onRestoreInstanceState(getParent().viewModel.scrollViewStates.get(getClass())));

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        getParent().viewModel.scrollViewStates.put(getClass(), recyclerView.getLayoutManager().onSaveInstanceState());
        getParent().saveSearchBarState(getClass());
    }

    private void initSearchBarTextWatcher() {
        if(textWatcher != null) return;

        textWatcher = new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.updateData(filter(s.toString()));
            }
        };

        getParent().addSearchBarTextWatcher(textWatcher);
    }

    private List<T> filter(String query) {
        if(query == null || query.isEmpty()) return allElements;

        String lowerQuery = query.toLowerCase();

        Comparator<T> comparator = Comparator
                .comparing((T element) -> element.getName().equalsIgnoreCase(lowerQuery))
                .thenComparing(element -> element.getName().toLowerCase().startsWith(lowerQuery)).reversed();

        return allElements.stream()
                .filter(element -> element.getName().toLowerCase().contains(lowerQuery))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public HomeFragment getParent() {
        return ((HomeFragment) getParentFragment());
    }
}
