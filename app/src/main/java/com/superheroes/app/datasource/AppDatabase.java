package com.superheroes.app.datasource;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MarvelHeroDatabase.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HeroesDao heroesDao();
}