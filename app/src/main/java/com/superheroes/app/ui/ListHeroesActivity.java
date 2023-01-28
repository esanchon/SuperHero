package com.superheroes.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.superheroes.app.R;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.presenter.ListHeroesPresenter;
import com.superheroes.app.presenter.ListHeroesViewTranslator;

import java.util.List;

public class ListHeroesActivity extends AppCompatActivity implements ListHeroesViewTranslator {

    private ListHeroesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //review recreation of the presenter
        presenter = new ListHeroesPresenter(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadData(List<MarvelHero> heroList) {

    }

    @Override
    public void showError(String message) {

    }
}