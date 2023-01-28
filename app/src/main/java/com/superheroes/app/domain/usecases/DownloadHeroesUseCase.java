package com.superheroes.app.domain.usecases;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.repository.MarvelHeroeRepository;
import java.util.List;
import java.util.concurrent.Callable;

public class DownloadHeroesUseCase implements Callable<Result<List<MarvelHero>>> {
        MarvelHeroeRepository marvelHeroeRepository;
        Boolean isLocal = false;

        public DownloadHeroesUseCase(MarvelHeroeRepository repository) {
            marvelHeroeRepository = repository;
        }

        public void setSourceType(Boolean isLocal) {
            this.isLocal = isLocal;
        }

        @Override
        public Result<List<MarvelHero>> call() throws Exception {
            return marvelHeroeRepository.getAllHeroes(isLocal);
        }
}

