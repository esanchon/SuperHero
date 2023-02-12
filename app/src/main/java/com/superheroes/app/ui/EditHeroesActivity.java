package com.superheroes.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.superheroes.app.R;
import com.superheroes.app.domain.models.MarvelHero;
import com.superheroes.app.presenter.EditHeroesPresenter;
import com.superheroes.app.presenter.EditHeroesViewTranslator;
import com.superheroes.app.presenter.ListHeroesPresenter;
import com.superheroes.app.presenter.ListHeroesViewTranslator;

import java.util.List;

public class EditHeroesActivity extends AppCompatActivity implements EditHeroesViewTranslator {

    private EditHeroesPresenter presenter;
    private static Boolean isUpdate = false;
    private  MarvelHero currentHeroSelected;
    private EditText edit_name;
    private Button btn_save;


    private ProgressBar mLoader;

    public static void start(Context context, MarvelHero marvelHero, boolean isUpdateScreen){
        Intent intent = new Intent(context, EditHeroesActivity.class);
        isUpdate = isUpdateScreen;
        intent.putExtra("hero", marvelHero);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null &&savedInstanceState.getParcelable("hero") != null){
            currentHeroSelected = savedInstanceState.getParcelable("hero");
        } else {
            Bundle extras = getIntent().getExtras();
            currentHeroSelected = extras.getParcelable("hero");
        }


        setContentView(R.layout.activity_edit_heroes);

        mLoader = (ProgressBar) findViewById(R.id.pb_loader);
        mLoader.setIndeterminate(true);

        //review recreation of the presenter
        presenter = new EditHeroesPresenter(this);


        btn_save = findViewById(R.id.btn_save);
        btn_save.setText(isUpdate ? "Update" : "Remove");
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUpdate){
                    currentHeroSelected.setName(edit_name.getText().toString());
                    presenter.onUpdate(currentHeroSelected);
                }else{
                    presenter.onDelete(currentHeroSelected);
                }
            }
        });
        edit_name = findViewById(R.id.edit_name);
        edit_name.setText(currentHeroSelected.getName());

        getSupportActionBar().setTitle(isUpdate ?"Editar" : "Borrar");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable("hero", currentHeroSelected);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void showProgress() {
        mLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mLoader.setVisibility(View.GONE);
    }

    @Override
    public void notifyChanges(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
}