package com.superheroes.app.datasource;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.superheroes.app.domain.models.MarvelHero;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MarvelHereoRemoteDataSource {

    private String API_URL = "https://www.superheroapi.com/api.php/10160402389059699/search/super";

    public MarvelHereoRemoteDataSource() {

    }

    public List<MarvelHero> getAllHereoes() throws Exception {

        List<MarvelHero> marvelHeroes = new ArrayList<>();
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        try {
            Request request = new Request.Builder()
                    .url(API_URL)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                //parse response
                Map<String, Object> jsonResponse = new Gson().fromJson(response.body().string(), Map.class);
                List<Map> remoteHeroes = (List)jsonResponse.get("results");
                for (Map hero : remoteHeroes) {
                    MarvelHero marvelHero = new MarvelHero();
                    marvelHero.setName((String)hero.get("name"));
                    marvelHero.setRealName((String)((Map)hero.get("biography")).get("full-name"));
                    marvelHero.setPhoto((String)((Map)hero.get("image")).get("url"));
                    marvelHero.setGender((String)((Map)hero.get("appearance")).get("gender"));
                    marvelHero.setPower((String)((Map)hero.get("powerstats")).get("power"));
                    marvelHero.setIntelligence((String)((Map)hero.get("powerstats")).get("intelligence"));
                    marvelHero.setIntelligence((String)((Map)hero.get("connections")).get("group-affiliation"));
                    marvelHeroes.add(marvelHero);
                }
            } else {
                return marvelHeroes;
            }
        } catch (JsonSyntaxException parseException) {
            throw parseException;
        } catch (Exception e) {
            throw e;
        }

        return marvelHeroes;
    }
}
