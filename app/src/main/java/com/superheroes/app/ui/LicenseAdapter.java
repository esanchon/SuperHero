package com.superheroes.app.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.superheroes.app.R;

import java.util.ArrayList;
import java.util.List;

public class LicenseAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<String> licenses;

    private static final int HEADER = 0;
    private static final int OTHER = 1;


    public LicenseAdapter() {
        this.licenses = new ArrayList<>();
        licenses.add("Room Persistence Library");
        licenses.add("Glide Library");
        licenses.add("Okhttp3 Library");
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder result;
        if (viewType == HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.about_licence_row, parent, false);
            result = new HeaderLicenseViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.about_licence_row, parent, false);
            result = new LicenseViewHolder(view);
        }
        return result;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == HEADER)
            return HEADER;
        else
            return OTHER;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof LicenseViewHolder) {
            ((LicenseViewHolder) holder).render(licenses.get(position-1));
        } else {
            ((HeaderLicenseViewHolder) holder).render();
        }
    }

    @Override
    public int getItemCount() {
        return licenses.size()+1;
    }
}