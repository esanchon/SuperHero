package com.superheroes.app.datasource;

import androidx.room.Room;

import com.superheroes.app.HeroesApplication;
import com.superheroes.app.domain.models.MarvelHero;

import java.util.ArrayList;
import java.util.List;

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
            marvelHero.setLatitude(hero.getLatitude());
            marvelHero.setLongitude(hero.getLongitude());

            marvelHeroesDatabase.add(marvelHero);
        }
        heroesDao.insertAll(marvelHeroesDatabase);
    }

       public List<MarvelHero> getAllHeroes() throws Exception {
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
               marvelHero.setLatitude(heroDatabase.getLatitude());
               marvelHero.setLongitude(heroDatabase.getLongitude());
               marvelHeroes.add(marvelHero);
           }
            return marvelHeroes;
        }

        //update
        public Boolean editHero(MarvelHero hero) throws Exception {
              HeroesDao heroesDao = db.heroesDao();

             try {
                 MarvelHeroDatabase marvelHeroDatabase = new MarvelHeroDatabase();
                 marvelHeroDatabase.setId(hero.getId());
                 marvelHeroDatabase.setName(hero.getName());
                 marvelHeroDatabase.setRealName(hero.getRealName());
                 marvelHeroDatabase.setPhoto(hero.getPhoto());
                 marvelHeroDatabase.setGender(hero.getGender());
                 marvelHeroDatabase.setPower(hero.getPower());
                 marvelHeroDatabase.setIntelligence(hero.getIntelligence());
                 marvelHeroDatabase.setGroups(hero.getIntelligence());
                 marvelHeroDatabase.setLatitude(hero.getLatitude());
                 marvelHeroDatabase.setLongitude(hero.getLongitude());

                 heroesDao.update(marvelHeroDatabase);
                 return true;
             } catch (Exception e) {
                 return false;
             }
        }

    public Boolean deleteHero(MarvelHero hero) {
        HeroesDao heroesDao = db.heroesDao();

        try {
            MarvelHeroDatabase marvelHeroDatabase = new MarvelHeroDatabase();
            marvelHeroDatabase.setId(hero.getId());
            marvelHeroDatabase.setName(hero.getName());
            marvelHeroDatabase.setRealName(hero.getRealName());
            marvelHeroDatabase.setPhoto(hero.getPhoto());
            marvelHeroDatabase.setGender(hero.getGender());
            marvelHeroDatabase.setPower(hero.getPower());
            marvelHeroDatabase.setIntelligence(hero.getIntelligence());
            marvelHeroDatabase.setGroups(hero.getIntelligence());
            marvelHeroDatabase.setLatitude(hero.getLatitude());
            marvelHeroDatabase.setLongitude(hero.getLongitude());

            heroesDao.delete(marvelHeroDatabase);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clearHeroes() {
        try {
            HeroesDao heroesDao = db.heroesDao();
            heroesDao.deleteAll();
        } catch (Exception e) {
        }
    }
}
