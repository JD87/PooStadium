package com.example.david.poostadium;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

public class StartActivity extends AppCompatActivity {

    Button btn_lets;
    SeekBar skb_playrs;
    Spinner spr_classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        spr_classes = findViewById(R.id.spr_classes);
        skb_playrs = findViewById(R.id.skb_playrs);
        btn_lets = findViewById(R.id.btn_lets);

        final Intent i = new Intent(this, MainActivity.class);

        ArrayAdapter arrayadapter = ArrayAdapter.createFromResource(this,R.array.charclass,R.layout.support_simple_spinner_dropdown_item);
        arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spr_classes.setAdapter(arrayadapter);

        skb_playrs.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_lets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
                finish();
            }
        });
    }
}
