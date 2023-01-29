package com.superheroes.app.ui;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.superheroes.app.R;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.presenter.ListHeroesPresenter;
import com.superheroes.app.ui.HeroViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HeroesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MarvelHero> superHeroes;
    private ListHeroesPresenter presenter;

    public HeroesAdapter(ListHeroesPresenter presenter) {
        this.presenter = presenter;
        this.superHeroes = new ArrayList<>();
    }

    public void addAll(List<MarvelHero> heroes) {
        superHeroes.clear();
        superHeroes.addAll(heroes);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marvel_hero_row, parent, false);
        return new HeroViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.v("Position", String.valueOf(position));
        HeroViewHolder superHeroViewHolder = (HeroViewHolder) holder;
        MarvelHero superHero = superHeroes.get(position);
        superHeroViewHolder.render(superHero);
    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }
}