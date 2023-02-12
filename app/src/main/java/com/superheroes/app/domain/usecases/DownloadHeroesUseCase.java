package com.superheroes.app.domain.usecases;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.repository.MarvelHeroRepository;
import java.util.List;
import java.util.concurrent.Callable;

public class DownloadHeroesUseCase implements Callable<Result<List<MarvelHero>>> {
        MarvelHeroRepository marvelHeroRepository;
        Boolean isLocal = false;

        public DownloadHeroesUseCase(MarvelHeroRepository repository) {
            marvelHeroRepository = repository;
        }

        public void setSourceType(Boolean isLocal) {
            this.isLocal = isLocal;
        }

        @Override
        public Result<List<MarvelHero>> call() throws Exception {
            return marvelHeroRepository.getAllHeroes(isLocal);
        }
}

