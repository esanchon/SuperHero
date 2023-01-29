package com.superheroes.app.presenter;

import android.view.View;

import com.superheroes.app.domain.models.MarvelHero;

import java.util.List;

public interface EditHeroesViewTranslator {

    void showProgress();

    void hideProgress();

    void notifyChanges(String message);
}
