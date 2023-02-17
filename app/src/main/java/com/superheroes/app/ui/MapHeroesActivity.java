package com.superheroes.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.superheroes.app.databinding.ActivityMapsBinding;
import com.superheroes.app.R;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.presenter.ListHeroesPresenter;
import com.superheroes.app.presenter.MapHeroesPresenter;
import com.superheroes.app.presenter.MapHeroesViewTranslator;

import java.util.ArrayList;
import java.util.List;

public class MapHeroesActivity extends AppCompatActivity implements OnMapReadyCallback, MapHeroesViewTranslator {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private MapHeroesPresenter presenter;
    private TextView mEmptyText;
    private List<MarvelHero> superHeroes;

    public static void start(Context context) {
        Intent intent = new Intent(context, MapHeroesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new MapHeroesPresenter(this, false);
        mEmptyText = findViewById(R.id.tv_empty_list);
        superHeroes = new ArrayList<>();

        getSupportActionBar().setTitle(R.string.ubicacion_heroes);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        for (int i = 0; i < superHeroes.size(); i++) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(superHeroes.get(i).getLatitude(), superHeroes.get(i).getLongitude()))
                    .title(superHeroes.get(i).getName())
                    .snippet(superHeroes.get(i).getRealName()));
        }

        LatLng spain = new LatLng(40.4646, -3.86456);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(spain));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(5));

    }

    @Override
    public void loadData(List<MarvelHero> heroList) {
        this.superHeroes = heroList;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void showProgress() {}

    @Override
    public void hideProgress()  {}

    @Override
    public void showError(int message) {
        mEmptyText.setVisibility(View.VISIBLE);
        mEmptyText.setText(getString(message));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.location_content);
        menuItem.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.update_content:
                presenter.onMenuUpdateClicked();
                break;
            case R.id.about_content:
                presenter.onMenuAboutClicked(getApplicationContext());
                break;
            case R.id.settings_content:
                presenter.onMenuSettingsClicked(getApplicationContext());
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
