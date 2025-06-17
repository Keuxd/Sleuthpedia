package com.example.sleuthpedia.fragments.navigation.recyclerviewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<T> elements;

    private int navigationRowId;
    protected OnItemClickListener<T> listener;

    public interface OnItemClickListener<T> {
        void onItemClick(T element);
    }

    public abstract void bind(View itemView, final T element, final BaseRecyclerViewAdapter.OnItemClickListener<T> listener);

    public BaseRecyclerViewAdapter(int navigationRowId, List<T> elements, OnItemClickListener<T> listener) {
        this.navigationRowId = navigationRowId;
        this.elements = elements;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(navigationRowId, parent, false);
        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bind(holder.itemView, elements.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public void updateData(List<T> newElements) {
        this.elements = newElements;
        notifyDataSetChanged();
    }

    public void loadAssetsImageInto(String fileName, String subFolder,ImageView into) {
        String imagePath = String.format("file:///android_asset/images/%s/%s.webp", subFolder, fileName);
        Glide.with(into.getContext()).load(imagePath).into(into);
    }
}
