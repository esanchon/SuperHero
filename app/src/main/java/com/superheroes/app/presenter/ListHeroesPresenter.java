package com.superheroes.app.presenter;

import android.content.Context;
import android.view.View;

import com.superheroes.app.R;
import com.superheroes.app.datasource.MarvelHeroRemoteDataSource;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.domain.usecases.DownloadHeroesUseCase;
import com.superheroes.app.domain.usecases.Result;
import com.superheroes.app.domain.usecases.TaskRunner;
import com.superheroes.app.repository.MarvelHeroRepository;
import com.superheroes.app.ui.DetailHeroesActivity;
import com.superheroes.app.ui.EditHeroesActivity;
import com.superheroes.app.ui.ListLicenseActivity;
import com.superheroes.app.ui.MapHeroesFragment;
import com.superheroes.app.ui.SettingsActivity;

import java.util.ArrayList;
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
        DownloadHeroesUseCase useCase = new DownloadHeroesUseCase(new MarvelHeroRepository(new MarvelHeroRemoteDataSource()));
        useCase.setSourceType(isLocal);
        mTaskRunner.executeAsync(useCase, (result) -> {
            mView.hideProgress();
            if (result.status() == Result.Status.OK) {
                mView.loadData(result.data());
            } else {
                mView.showError(R.string.error_download_data);
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
        currentHeroSelected = marvelHero;
        DetailHeroesActivity.start(context, marvelHero);
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

    public void onMenuMapsClicked(Context context) {

        MapHeroesFragment.start(context);
    }

    public void onMenuSettingsClicked(Context context) {
        SettingsActivity.start(context);
    }
}
