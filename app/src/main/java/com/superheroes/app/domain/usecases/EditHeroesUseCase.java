package com.superheroes.app.domain.usecases;

import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.repository.MarvelHeroeRepository;

import java.util.List;
import java.util.concurrent.Callable;

public class EditHeroesUseCase implements Callable<Result<Boolean>> {
        MarvelHeroeRepository marvelHeroeRepository;
        MarvelHero hero;

        public EditHeroesUseCase(MarvelHeroeRepository repository) {
            marvelHeroeRepository = repository;
        }

        public void setHero(MarvelHero hero) {
            this.hero = hero;
        }

        @Override
        public Result<Boolean> call() throws Exception {
            return marvelHeroeRepository.editHero(hero);
        }
}

