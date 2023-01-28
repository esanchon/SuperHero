package com.superheroes.app.repository;

import androidx.annotation.NonNull;

import com.superheroes.app.datasource.MarvelHereoRemoteDataSource;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.domain.usecases.Result;

import java.io.IOException;
import java.util.List;



public class MarvelHeroeRepository {

    @NonNull
    MarvelHereoRemoteDataSource marvelHereoRemoteDataSource;

    public MarvelHeroeRepository(@NonNull MarvelHereoRemoteDataSource remoteDataSource) {
        marvelHereoRemoteDataSource = remoteDataSource;
    }

    @NonNull
    public Result<List<MarvelHero>> getAllHeroes() throws Exception {
        List<MarvelHero> response = null;
        try {
            response = marvelHereoRemoteDataSource.getAllHereoes();
            return new Result<>(Result.Status.OK, response);
        } catch (IOException exception) {
            return new Result<>(Result.Status.ERROR, null);
        }
    }


}
