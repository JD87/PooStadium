package com.example.david.poostadium;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    TextView txv_letsplay;
    SeekBar skb_playrs;
    Spinner spr_classes;
    int mode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        spr_classes = findViewById(R.id.spr_classes);
        skb_playrs = findViewById(R.id.skb_playrs);
        txv_letsplay = findViewById(R.id.txv_letsplay);

        final Intent pvc = new Intent(this, MainActivity.class);
        final Intent pvp = new Intent(this, MainActivity.class);
        final Intent wifi = new Intent(this, WifiActivity.class);

        txv_letsplay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

            if(mode == 0)
                startActivity(pvc);
            else if(mode == 1)
                startActivity(pvp);
            else if(mode == 2)
                startActivity(wifi);

            finish();

                return false;
            }
        });

        ArrayAdapter arrayadapter = ArrayAdapter.createFromResource(this,R.array.charclass,R.layout.support_simple_spinner_dropdown_item);
        arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spr_classes.setAdapter(arrayadapter);


        skb_playrs.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mode = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
