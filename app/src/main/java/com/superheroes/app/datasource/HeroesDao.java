package com.superheroes.app.datasource;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HeroesDao {

    @Query("SELECT * FROM marvelherodatabase")
    List<MarvelHeroDatabase> getAll();

    @Insert
    void insertAll(List<MarvelHeroDatabase> heroes);

    @Delete
    void delete(MarvelHeroDatabase heroe);

    @Update
    void update(MarvelHeroDatabase heroe);

}
