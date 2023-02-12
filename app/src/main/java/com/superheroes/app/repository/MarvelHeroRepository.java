package com.superheroes.app.repository;

import androidx.annotation.NonNull;

import com.superheroes.app.datasource.MarvelHeroRemoteDataSource;
import com.superheroes.app.datasource.MarvelHeroeLocalDataSource;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.domain.usecases.Result;

import java.io.IOException;
import java.util.List;



public class MarvelHeroRepository {

    @NonNull
    MarvelHeroRemoteDataSource marvelHeroRemoteDataSource;

    @NonNull
    MarvelHeroeLocalDataSource marvelHeroLocalDataSource;

    public MarvelHeroRepository(@NonNull MarvelHeroRemoteDataSource remoteDataSource) {
        marvelHeroRemoteDataSource = remoteDataSource;
        marvelHeroLocalDataSource = new MarvelHeroeLocalDataSource();
    }

    @NonNull
    public Result<List<MarvelHero>> getAllHeroes(Boolean isLocal) throws Exception {
        List<MarvelHero> response = null;
        try {
            if(isLocal){
                response = marvelHeroLocalDataSource.getAllHereoes();
            } else {
                response = marvelHeroRemoteDataSource.getAllHereoes();
                marvelHeroLocalDataSource.clearHeroes();
                marvelHeroLocalDataSource.saveHeroes(response);
            }
            //if islocal maybe new status to inform OK_LOCAL or OK_REMOTE
            return new Result<>(Result.Status.OK, response);
        } catch (IOException exception) {
            return new Result<>(Result.Status.ERROR, null);
        } catch (Exception exception) {
            return new Result<>(Result.Status.ERROR, null);
        }
    }

    //method remove

    //method update
    @NonNull
    public Result<Boolean> editHero(MarvelHero hero) throws Exception {

        try {
            Boolean response = marvelHeroLocalDataSource.editHero(hero);
            return new Result<>(Result.Status.OK, response);
        } catch (IOException exception) {
            return new Result<>(Result.Status.ERROR, false);
        } catch (Exception exception) {
            return new Result<>(Result.Status.ERROR, false);
        }
    }

    public Result<Boolean> deleteHero(MarvelHero hero) {


        try {
            Boolean response = marvelHeroLocalDataSource.deleteHero(hero);
            return new Result<>(Result.Status.OK, response);
        } catch (Exception exception) {
            return new Result<>(Result.Status.ERROR, false);
        }
    }
}
