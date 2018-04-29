package com.example.david.poostadium;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView txv_namep1, txv_namep2,txv_lifep1, txv_lifep2;
    ImageView imv_p1, imv_p2, imv_slash, imv_potion;

    float x_dwn = 0, x_up = 0, y_dwn = 0, y_up = 0;

    MainCharacter character1 = new MainCharacter();
    MainCharacter character2 = new MainCharacter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv_namep1 = findViewById(R.id.txv_namep1);
        txv_namep2 = findViewById(R.id.txv_namep2);
        txv_lifep1 = findViewById(R.id.txv_lifep1);
        txv_lifep2 = findViewById(R.id.txv_lifep2);

        imv_p1 = findViewById(R.id.imv_p1);
        imv_p2 = findViewById(R.id.imv_p2);
        imv_slash = findViewById(R.id.imv_slash);

        txv_lifep1.setText(character1.getLife() + "");
        txv_lifep2.setText(character2.getLife() + "");

        txv_namep1.setText(character1.getName());
        txv_namep2.setText(character1.getName());
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x_dwn = event.getX();
                y_dwn = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x_up = event.getX();
                y_up = event.getY();
                float dx = Math.abs(x_up - x_dwn);
                float dy = Math.abs(y_up - y_dwn);
                if (dx>150)
                    if(x_up > x_dwn){
                        imv_slash.setRotationY(0);
                        fade_animation(imv_slash,250);
                        ouch_animation(imv_p2,100,20);
                        character1.attack(character2);
                        txv_lifep2.setText(character2.getLife() + "");
                    }
                    else {
                        imv_slash.setRotationY(180);
                        fade_animation(imv_slash,250);
                        ouch_animation(imv_p1,100,-20);
                        character2.attack(character1);
                        txv_lifep1.setText(character1.getLife() + "");
                    }
                if (dy>150){
                    if(y_up > y_dwn) {
                        Log.i("fingering tag", "downers");
                    }
                    else {
                        Log.i("fingering tag", "uppers");
                    }
                    // TODO
                }
                break;
        }

        return true;
    }

    public void fade_animation(final ImageView imView, int duration_ms){

        Animation my_anim = new AlphaAnimation(1,0);
        my_anim.setDuration(duration_ms);

        my_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO
            }
        });

        imView.startAnimation(my_anim);

    }

    public void ouch_animation(final ImageView imView, int duration_ms, float bck){
        ObjectAnimator objanim_bck = ObjectAnimator.ofFloat(imView,"translationX",bck);
        ObjectAnimator objanim_fwd = ObjectAnimator.ofFloat(imView,"translationX", 0);
        objanim_bck.setDuration(duration_ms);
        objanim_fwd.setDuration(duration_ms);

        AnimatorSet my_animset = new AnimatorSet();
        my_animset.playSequentially(objanim_bck,objanim_fwd);
        my_animset.start();
    }
}
