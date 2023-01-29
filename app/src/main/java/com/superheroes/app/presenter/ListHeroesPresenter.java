package com.superheroes.app.presenter;


import android.content.Context;
import android.view.View;

import com.superheroes.app.datasource.MarvelHereoRemoteDataSource;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.domain.usecases.DownloadHeroesUseCase;
import com.superheroes.app.domain.usecases.Result;
import com.superheroes.app.domain.usecases.TaskRunner;
import com.superheroes.app.repository.MarvelHeroeRepository;
import com.superheroes.app.ui.EditHeroesActivity;
import com.superheroes.app.ui.ListLicenseActivity;

import java.util.List;

public class ListHeroesPresenter  {

    private ListHeroesViewTranslator mView;
    private TaskRunner mTaskRunner = new TaskRunner();

    private MarvelHero currentHeroSelected;

    public ListHeroesPresenter(ListHeroesViewTranslator view, Boolean isFirstTime) {
        mView = view;
        mView.showProgress();

        downloadData(!isFirstTime);
    }

    public void downloadData(Boolean isLocal) {
        mView.showProgress();
        DownloadHeroesUseCase useCase = new DownloadHeroesUseCase(new MarvelHeroeRepository(new MarvelHereoRemoteDataSource()));
        useCase.setSourceType(isLocal);
        mTaskRunner.executeAsync(useCase, (result) -> {
            mView.hideProgress();
            if (result.status() == Result.Status.OK) {
                mView.loadData(result.data());
            } else {
                mView.showError("No se puedo cargar la lista");
            }
        });
    }


    public void onResume() {
        if(currentHeroSelected != null) {
            downloadData(true);
            currentHeroSelected = null;
        }
    }

    public void onMarvelHeroClick(Context context, MarvelHero marvelHero) {
        //TODO open detail activity
    }

    public void onMarvelHeroLongClick(View v, MarvelHero marvelHero) {
        currentHeroSelected = marvelHero;
        mView.openEditDialog(v);
    }

    public void onMenuClicked(Context context, Boolean isUpdate) {
        EditHeroesActivity.start(context, currentHeroSelected, isUpdate);
    }

    public void onMenuUpdateClicked() {
        downloadData(false);
    }

    public void onMenuAboutClicked(Context context) {
        ListLicenseActivity.start(context);
    }
}
