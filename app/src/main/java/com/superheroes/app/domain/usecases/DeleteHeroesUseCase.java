package com.superheroes.app.domain.usecases;

import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.repository.MarvelHeroeRepository;

import java.util.concurrent.Callable;

public class DeleteHeroesUseCase implements Callable<Result<Boolean>> {
        MarvelHeroeRepository marvelHeroeRepository;
        MarvelHero hero;

        public DeleteHeroesUseCase(MarvelHeroeRepository repository) {
            marvelHeroeRepository = repository;
        }

        public void setHero(MarvelHero hero) {
            this.hero = hero;
        }

        @Override
        public Result<Boolean> call() throws Exception {
            return marvelHeroeRepository.deleteHero(hero);
        }
}

