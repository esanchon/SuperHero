package com.superheroes.app.ui;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.superheroes.app.R;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.presenter.ListHeroesPresenter;

public class LicenseViewHolder extends RecyclerView.ViewHolder {


    private View itemView;
    private TextView licensetitle;
    public LicenseViewHolder(View itemView) {
        super(itemView);
        licensetitle = itemView.findViewById(R.id.license_title);
    }

    public void render(String title) {
        if (title != null) {
            licensetitle.setText(title);
        }
    }
}