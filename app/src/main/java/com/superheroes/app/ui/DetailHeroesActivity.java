package com.superheroes.app.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.superheroes.app.HeroesApplication;
import com.superheroes.app.R;
import com.superheroes.app.domain.models.MarvelHero;

import org.w3c.dom.Text;

public class DetailHeroesActivity extends AppCompatActivity {

    private ScrollView mscrollView;
    private ImageView mImageView;
    private MarvelHero currentHeroSelected;
    Intent intent;
    String detalleHeroe;
    String signature;
    private SharedPreferences mSharedPreferences;

    public static void start(Context context, MarvelHero marvelHero) {
        Intent intent = new Intent(context, DetailHeroesActivity.class);
        intent.putExtra("hero", marvelHero);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.getParcelable("hero") != null) {
            currentHeroSelected = savedInstanceState.getParcelable("hero");
        } else {
            Bundle extras = getIntent().getExtras();
            currentHeroSelected = extras.getParcelable("hero");
        }

        setContentView(R.layout.activity_detail_hero);

        mscrollView = findViewById(R.id.sv_hero);
        mImageView = findViewById(R.id.iv_hero_image);
        TextView name = findViewById(R.id.tv_hero_name);
        TextView realName = findViewById(R.id.tv_hero_real_name);
        TextView gender = findViewById(R.id.tv_hero_gender);
        TextView power = findViewById(R.id.tv_hero_power);
        TextView intelligence = findViewById(R.id.tv_hero_intelligence);
        TextView group = findViewById(R.id.tv_hero_group);

        name.setText(getString(R.string.hero_detail_name) + currentHeroSelected.getName());
        realName.setText(getString(R.string.hero_detail_realname) + currentHeroSelected.getRealName());
        gender.setText(getString(R.string.hero_detail_gender) + currentHeroSelected.getGender());
        power.setText(getString(R.string.hero_detail_power) + currentHeroSelected.getPower());
        intelligence.setText(getString(R.string.hero_detail_intelligence) + currentHeroSelected.getIntelligence());
        group.setText(getString(R.string.hero_detail_groups) + currentHeroSelected.getGroups());

        Glide.with(this).load(currentHeroSelected.getPhoto()).into(mImageView);

        getSupportActionBar().setTitle(getString(R.string.detail));


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable("hero", currentHeroSelected);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_detailhero, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_content:
                //TODO create presenter
                //presenter.onMenuShareClicked();

                mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(HeroesApplication.getAppContext());

                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                detalleHeroe = getResources().getString(R.string.hero_detail_name) + currentHeroSelected.getName()
                                +"\n" + getResources().getString(R.string.hero_detail_power) + currentHeroSelected.getPower()
                                +"\n" + mSharedPreferences.getString("share_hero_signature","");;
                intent.putExtra(Intent.EXTRA_TEXT, detalleHeroe);
                Intent shareIntent = Intent.createChooser(intent, getString(R.string.share_hero));
                startActivity(shareIntent);

                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

