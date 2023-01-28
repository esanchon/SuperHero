package com.superheroes.app.datasource;

import androidx.room.Room;

import com.superheroes.app.HeroesApplication;
import com.superheroes.app.domain.models.MarvelHero;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarvelHeroeLocalDataSource {

    private AppDatabase db;

    public MarvelHeroeLocalDataSource() {
        db = Room.databaseBuilder(HeroesApplication.getAppContext(),
                AppDatabase.class, "database-heroes-list").build();
    }

    public void saveHeroes(List<MarvelHero> heroes) throws Exception {
        HeroesDao heroesDao = db.heroesDao();

        List<MarvelHeroDatabase> marvelHeroesDatabase = new ArrayList<>();
        for (MarvelHero hero : heroes) {
            MarvelHeroDatabase marvelHero = new MarvelHeroDatabase();
            marvelHero.setId(hero.getId());
            marvelHero.setName(hero.getName());
            marvelHero.setRealName(hero.getRealName());
            marvelHero.setPhoto(hero.getPhoto());
            marvelHero.setGender(hero.getGender());
            marvelHero.setPower(hero.getPower());
            marvelHero.setIntelligence(hero.getIntelligence());
            marvelHero.setGroups(hero.getIntelligence());
            marvelHeroesDatabase.add(marvelHero);
        }
        heroesDao.insertAll(marvelHeroesDatabase);
    }

       public List<MarvelHero> getAllHereoes() throws Exception {
           HeroesDao heroesDao = db.heroesDao();

           List<MarvelHero> marvelHeroes = new ArrayList<>();
           for (MarvelHeroDatabase heroDatabase : heroesDao.getAll()) {
               MarvelHero marvelHero = new MarvelHero();
               marvelHero.setId(heroDatabase.getId());
               marvelHero.setName(heroDatabase.getName());
               marvelHero.setRealName(heroDatabase.getRealName());
               marvelHero.setPhoto(heroDatabase.getPhoto());
               marvelHero.setGender(heroDatabase.getGender());
               marvelHero.setPower(heroDatabase.getPower());
               marvelHero.setIntelligence(heroDatabase.getIntelligence());
               marvelHero.setGroups(heroDatabase.getIntelligence());
               marvelHeroes.add(marvelHero);
           }
            return marvelHeroes;
        }

        //update
     //remove
}
