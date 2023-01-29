package com.superheroes.app.repository;

import androidx.annotation.NonNull;

import com.superheroes.app.datasource.MarvelHereoRemoteDataSource;
import com.superheroes.app.datasource.MarvelHeroeLocalDataSource;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.domain.usecases.Result;

import java.io.IOException;
import java.util.List;



public class MarvelHeroeRepository {

    @NonNull
    MarvelHereoRemoteDataSource marvelHereoRemoteDataSource;

    @NonNull
    MarvelHeroeLocalDataSource marvelHereoLocalDataSource;

    public MarvelHeroeRepository(@NonNull MarvelHereoRemoteDataSource remoteDataSource) {
        marvelHereoRemoteDataSource = remoteDataSource;
        marvelHereoLocalDataSource = new MarvelHeroeLocalDataSource();
    }

    @NonNull
    public Result<List<MarvelHero>> getAllHeroes(Boolean isLocal) throws Exception {
        List<MarvelHero> response = null;
        try {
            if(isLocal){
                response = marvelHereoLocalDataSource.getAllHereoes();
            } else {
                response = marvelHereoRemoteDataSource.getAllHereoes();
                marvelHereoLocalDataSource.saveHeroes(response);
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
            Boolean response = marvelHereoLocalDataSource.editHero(hero);
            return new Result<>(Result.Status.OK, response);
        } catch (IOException exception) {
            return new Result<>(Result.Status.ERROR, false);
        } catch (Exception exception) {
            return new Result<>(Result.Status.ERROR, false);
        }
    }

}
