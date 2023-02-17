package com.superheroes.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.superheroes.app.HeroesApplication;
import com.superheroes.app.R;
import com.superheroes.app.datasource.AppDatabase;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.presenter.ListHeroesPresenter;
import com.superheroes.app.presenter.ListHeroesViewTranslator;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class ListHeroesActivity extends AppCompatActivity implements ListHeroesViewTranslator {

    private ListHeroesPresenter presenter;
    private RecyclerView mRecyclerView;
    private HeroesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mEmptyText;
    private ProgressBar mLoader;
    private SharedPreferences mSharedPreferences;


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
        switch (item.getItemId()) {
            case R.id.update_content:
                presenter.onMenuUpdateClicked();
                break;
            case R.id.about_content:
                presenter.onMenuAboutClicked(getApplicationContext());
                break;
            case R.id.location_content:
                presenter.onMenuMapsClicked(getApplicationContext());
                break;
            case R.id.settings_content:
                presenter.onMenuSettingsClicked(getApplicationContext());
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
    public void showError(int message) {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);
        mEmptyText.setText(getString(message));
    }

    @Override
    public void openEditDialog(View v) {
        PopupMenu popup = new PopupMenu(getApplicationContext(), v.findViewById(R.id.tv_hero_name));
        popup.getMenuInflater().inflate(R.menu.pop_up, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                presenter.onMenuClicked(getApplicationContext(), R.id.add == item.getItemId());
                return true;
            }
        });
        popup.show();//showing popup menu// show popup like dropdown list
    }

    @Override
    public void sendNotification(int message) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(HeroesApplication.getAppContext());

        if (mSharedPreferences.getBoolean("notifications_enabled",false)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }

            String CHANNEL_ID = "SUPERHERO_CHANNEL";

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "SUPERHERO_CHANNEL", NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
            }

            Intent intent = new Intent(getApplicationContext(), ListHeroesActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_super_background)
                    .setContentTitle(getString(R.string.SuperHeroes))
                    .setContentText(getString(R.string.Downloaded_heroes) + mAdapter.getItemCount() + getString(R.string.space_superheroes))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            notificationManager.notify(0, builder.build());
        }

    }
}

