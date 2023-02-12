package com.superheroes.app.presenter;


import com.superheroes.app.R;
import com.superheroes.app.datasource.MarvelHeroRemoteDataSource;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.domain.usecases.DeleteHeroesUseCase;
import com.superheroes.app.domain.usecases.EditHeroesUseCase;
import com.superheroes.app.domain.usecases.Result;
import com.superheroes.app.domain.usecases.TaskRunner;
import com.superheroes.app.repository.MarvelHeroRepository;

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
        DeleteHeroesUseCase useCase = new DeleteHeroesUseCase(new MarvelHeroRepository(new MarvelHeroRemoteDataSource()));
        useCase.setHero(marvelHero);
        mTaskRunner.executeAsync(useCase, (result) -> {
            mView.hideProgress();
            if (result.status() == Result.Status.OK) {
                mView.notifyChanges(R.string.remove_ok);
            } else {
                mView.notifyChanges(R.string.remove_error);
            }
        });
    }

    public void onUpdate(MarvelHero marvelHero) {
        mView.showProgress();
        EditHeroesUseCase useCase = new EditHeroesUseCase(new MarvelHeroRepository(new MarvelHeroRemoteDataSource()));
        useCase.setHero(marvelHero);
        mTaskRunner.executeAsync(useCase, (result) -> {
            mView.hideProgress();
            if (result.status() == Result.Status.OK) {
                mView.notifyChanges(R.string.modify_ok);
            } else {
                mView.notifyChanges(R.string.modify_error);
            }
        });
    }


}
