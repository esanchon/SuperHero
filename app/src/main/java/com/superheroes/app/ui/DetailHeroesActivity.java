package com.superheroes.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.superheroes.app.R;
import com.superheroes.app.domain.models.MarvelHero;

import org.w3c.dom.Text;

public class DetailHeroesActivity extends AppCompatActivity{

    private ScrollView mscrollView;
    private ImageView mImageView;

    private static MarvelHero currentHeroSelected;

    public static void start(Context context, MarvelHero marvelHero) {
        Intent intent = new Intent(context, DetailHeroesActivity.class);
        currentHeroSelected = marvelHero;
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hero);

        mscrollView = findViewById(R.id.sv_hero);
        mImageView = findViewById(R.id.iv_hero_image);
        TextView name = findViewById(R.id.tv_hero_name);
        TextView realName = findViewById(R.id.tv_hero_real_name);
        TextView gender = findViewById(R.id.tv_hero_gender);
        TextView power = findViewById(R.id.tv_hero_power);
        TextView intelligence = findViewById(R.id.tv_hero_intelligence);
        TextView group = findViewById(R.id.tv_hero_group);

        name.setText(currentHeroSelected.getName());
        realName.setText(currentHeroSelected.getRealName());
        gender.setText(currentHeroSelected.getGender());
        power.setText(currentHeroSelected.getPower());
        intelligence.setText(currentHeroSelected.getIntelligence());
        group.setText(currentHeroSelected.getGroups());

        Glide.with(this).load(currentHeroSelected.getPhoto()).into(mImageView);

        getSupportActionBar().setTitle("Detalle de Superheroe");



    }

}
