package com.superheroes.app.domain.usecases;

import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.repository.MarvelHeroRepository;

import java.util.concurrent.Callable;

public class EditHeroesUseCase implements Callable<Result<Boolean>> {
        MarvelHeroRepository marvelHeroRepository;
        MarvelHero hero;

        public EditHeroesUseCase(MarvelHeroRepository repository) {
            marvelHeroRepository = repository;
        }

        public void setHero(MarvelHero hero) {
            this.hero = hero;
        }

        @Override
        public Result<Boolean> call() throws Exception {
            return marvelHeroRepository.editHero(hero);
        }
}

