package com.superheroes.app.ui;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.superheroes.app.R;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.presenter.ListHeroesPresenter;

public class HeroViewHolder extends RecyclerView.ViewHolder {

    private ListHeroesPresenter presenter;
    private View itemView;
    private ImageView heroImage;
    private TextView heroName;

    public HeroViewHolder(View itemView, ListHeroesPresenter presenter) {
        super(itemView);

        this.presenter = presenter;
        this.itemView = itemView;

        heroImage = itemView.findViewById(R.id.im_hero_image);
        heroName = itemView.findViewById(R.id.tv_hero_name);
    }

    public void render(MarvelHero marvelHero) {
        hookListeners(marvelHero);
        if (marvelHero.getPhoto() != null) {
            Glide.with(getContext()).load(marvelHero.getPhoto()).into(heroImage);
        }
        if (marvelHero.getName() != null) {
            heroName.setText(marvelHero.getName());
        }
    }

    private void hookListeners(final MarvelHero marvelHero) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onMarvelHeroClick(getContext(), marvelHero);
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                presenter.onMarvelHeroLongClick(v, marvelHero);
                return true;
            }
        });
    }

    private Context getContext() {
        return itemView.getContext();
    }
}