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

import java.util.List;

public class MapHeroesPresenter {

    private MapHeroesViewTranslator mView;
    private TaskRunner mTaskRunner = new TaskRunner();


    public MapHeroesPresenter(MapHeroesViewTranslator view, Boolean isFirstTime) {
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

}
