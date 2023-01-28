package com.superheroes.app.presenter;


import com.superheroes.app.datasource.MarvelHereoRemoteDataSource;
import com.superheroes.app.domain.usecases.DownloadHeroesUseCase;
import com.superheroes.app.domain.usecases.Result;
import com.superheroes.app.domain.usecases.TaskRunner;
import com.superheroes.app.repository.MarvelHeroeRepository;

public class ListHeroesPresenter  {

    private ListHeroesViewTranslator mView;
    private TaskRunner mTaskRunner = new TaskRunner();

    public ListHeroesPresenter(ListHeroesViewTranslator view) {
        mView = view;
        mView.showProgress();

        initialize();
    }

    public void initialize() {
        mView.showProgress();
        mTaskRunner.executeAsync(new DownloadHeroesUseCase(new MarvelHeroeRepository(new MarvelHereoRemoteDataSource())), (result) -> {
            mView.hideProgress();
            if (result.status() == Result.Status.OK) {
                mView.loadData(result.data());
            } else {
                mView.showError("No se puedo cargar la lista");
            }
        });

    }
}