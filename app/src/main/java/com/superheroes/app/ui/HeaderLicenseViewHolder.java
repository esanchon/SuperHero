package com.superheroes.app.ui;


import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.superheroes.app.R;

public class HeaderLicenseViewHolder extends RecyclerView.ViewHolder {


    private View itemView;
    private TextView licensetitle;
    public HeaderLicenseViewHolder(View itemView) {
        super(itemView);
        licensetitle = itemView.findViewById(R.id.license_title);
    }

    public void render() {
        licensetitle.setText(R.string.seccion_librerias);
        licensetitle.setTextSize(17);
        licensetitle.setTextColor(Color.parseColor("#987654"));
    }
}