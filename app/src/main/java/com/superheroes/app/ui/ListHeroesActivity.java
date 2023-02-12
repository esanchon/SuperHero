package com.superheroes.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.superheroes.app.R;
import com.superheroes.app.datasource.AppDatabase;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.presenter.ListHeroesPresenter;
import com.superheroes.app.presenter.ListHeroesViewTranslator;

import java.util.List;

public class ListHeroesActivity extends AppCompatActivity implements ListHeroesViewTranslator {

    private ListHeroesPresenter presenter;
    private RecyclerView mRecyclerView;
    private HeroesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mEmptyText;
    private ProgressBar mLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_heroes);
        mRecyclerView = findViewById(R.id.rv_heroes_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mEmptyText = findViewById(R.id.tv_empty_list);
        mLoader = (ProgressBar) findViewById(R.id.pb_loader);
        mLoader.setIndeterminate(true);

        //review recreation of the presenter
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        presenter = new ListHeroesPresenter(this, preferences.getBoolean("isFirstTime", true));
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstTime", false);
        editor.apply();

        mAdapter = new HeroesAdapter(presenter);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
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
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
        mLoader.setVisibility(View.GONE);
    }

    @Override
    public void loadData(List<MarvelHero> heroList) {
        //show data
        mAdapter.addAll(heroList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);
        mEmptyText.setText(message);
    }

    @Override
    public void openEditDialog(View v) {
        PopupMenu popup = new PopupMenu(getApplicationContext(), v.findViewById(R.id.tv_hero_name));
        popup.getMenuInflater().inflate(R.menu.pop_up, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                presenter.onMenuClicked(getApplicationContext(),R.id.add== item.getItemId());
                return true;
            }
        });
        popup.show();//showing popup menu// show popup like dropdown list
    }
}