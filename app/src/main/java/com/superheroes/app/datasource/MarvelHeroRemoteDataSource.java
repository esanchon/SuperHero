package com.superheroes.app.datasource;

import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.superheroes.app.HeroesApplication;
import com.superheroes.app.domain.models.MarvelHero;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MarvelHeroRemoteDataSource {
   private String API_URL = "https://www.superheroapi.com/api.php/10160402389059699/search/super";
    private SharedPreferences mSharedPreferences;
    private static final double MIN_LATITUDE = 37.3;
    private static final double MAX_LATITUDE = 43.4;
    private static final double MIN_LONGITUDE = -9;
    private static final double MAX_LONGITUDE = 0;

    public MarvelHeroRemoteDataSource() {}

    public List<MarvelHero> getAllHereoes() throws Exception {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(HeroesApplication.getAppContext());
        try {
            API_URL = "https://www.superheroapi.com/api.php/10160402389059699/search/" + mSharedPreferences.getString("hero_search_key", "super");
        }
        catch (Exception e) {
            API_URL = "https://www.superheroapi.com/api.php/10160402389059699/search/super";
        }

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
                    marvelHero.setId(Integer.parseInt((String)hero.get("id")));
                    marvelHero.setName((String)hero.get("name"));
                    marvelHero.setRealName((String)((Map)hero.get("biography")).get("full-name"));
                    marvelHero.setPhoto((String)((Map)hero.get("image")).get("url"));
                    marvelHero.setGender((String)((Map)hero.get("appearance")).get("gender"));
                    marvelHero.setPower((String)((Map)hero.get("powerstats")).get("power"));
                    marvelHero.setIntelligence((String)((Map)hero.get("powerstats")).get("intelligence"));
                    marvelHero.setGroups((String)((Map)hero.get("connections")).get("group-affiliation"));
                    marvelHero.setLatitude(MIN_LATITUDE + (Math.random() * (MAX_LATITUDE - MIN_LATITUDE)));
                    marvelHero.setLongitude(MIN_LONGITUDE + (Math.random() * (MAX_LONGITUDE - MIN_LONGITUDE)));
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
