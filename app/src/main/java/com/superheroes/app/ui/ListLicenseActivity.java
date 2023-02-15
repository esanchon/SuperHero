package com.superheroes.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.superheroes.app.R;

public class ListLicenseActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LicenseAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static void start(Context context) {
        Intent intent = new Intent(context, ListLicenseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_licenses);
        mRecyclerView = findViewById(R.id.rv_license_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new LicenseAdapter();
        mRecyclerView.setAdapter(mAdapter);

        getSupportActionBar().setTitle(getString(R.string.license));
    }

}