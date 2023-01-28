package com.superheroes.app.domain.usecases;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.repository.MarvelHeroeRepository;
import java.util.List;
import java.util.concurrent.Callable;

public class DownloadHeroesUseCase implements Callable<Result<List<MarvelHero>>> {
        MarvelHeroeRepository marvelHeroeRepository;

        public DownloadHeroesUseCase(MarvelHeroeRepository repository) {
            marvelHeroeRepository = repository;
        }

        @Override
        public Result<List<MarvelHero>> call() throws Exception {
            return marvelHeroeRepository.getAllHeroes();
        }
}

