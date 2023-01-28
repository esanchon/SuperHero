package com.superheroes.app.presenter;

import com.superheroes.app.domain.models.MarvelHero;

import java.util.List;

public interface ListHeroesViewTranslator {

    void showProgress();

    void hideProgress();

    void loadData(List<MarvelHero> heroList);

    void showError(String message);
}
