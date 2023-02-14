package com.superheroes.app.presenter;

import android.view.View;

import com.superheroes.app.domain.models.MarvelHero;

import java.util.List;

public interface MapHeroesViewTranslator {

    void showProgress();

    void hideProgress();

    void loadData(List<MarvelHero> heroList);

    void showError(int message);
}
