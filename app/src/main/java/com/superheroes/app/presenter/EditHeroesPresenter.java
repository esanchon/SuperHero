package com.superheroes.app.presenter;


import android.content.Context;
import android.view.View;

import com.superheroes.app.datasource.MarvelHereoRemoteDataSource;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.domain.usecases.DeleteHeroesUseCase;
import com.superheroes.app.domain.usecases.DownloadHeroesUseCase;
import com.superheroes.app.domain.usecases.EditHeroesUseCase;
import com.superheroes.app.domain.usecases.Result;
import com.superheroes.app.domain.usecases.TaskRunner;
import com.superheroes.app.repository.MarvelHeroeRepository;

public class EditHeroesPresenter {

    private EditHeroesViewTranslator mView;
    private TaskRunner mTaskRunner = new TaskRunner();


    public EditHeroesPresenter(EditHeroesViewTranslator view) {
        mView = view;

        initialize();
    }

    public void initialize() {
        mView.hideProgress();
    }

    public void onDelete(MarvelHero marvelHero) {
        mView.showProgress();
        DeleteHeroesUseCase useCase = new DeleteHeroesUseCase(new MarvelHeroeRepository(new MarvelHereoRemoteDataSource()));
        useCase.setHero(marvelHero);
        mTaskRunner.executeAsync(useCase, (result) -> {
            mView.hideProgress();
            if (result.status() == Result.Status.OK) {
                mView.notifyChanges("Borrado correctamente");
            } else {
                mView.notifyChanges("No se pudo borrar el heroe");
            }
        });
    }

    public void onUpdate(MarvelHero marvelHero) {
        mView.showProgress();
        EditHeroesUseCase useCase = new EditHeroesUseCase(new MarvelHeroeRepository(new MarvelHereoRemoteDataSource()));
        useCase.setHero(marvelHero);
        mTaskRunner.executeAsync(useCase, (result) -> {
            mView.hideProgress();
            if (result.status() == Result.Status.OK) {
                mView.notifyChanges("Modificado correctamente");
            } else {
                mView.notifyChanges("No se pudo modificar el heroe");
            }
        });
    }


}
