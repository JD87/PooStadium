package com.example.david.poostadium;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class WifiActivity extends AppCompatActivity {


    TextView txv_namep1, txv_namep2,txv_lifep1, txv_lifep2;
    ImageView imv_p1, imv_p2, imv_slash, imv_potion;

    Fragment b_frag;

    float x_dwn = 0, x_up = 0, y_dwn = 0, y_up = 0;

    String ipadd, socket, p2response = "";

    Boolean frag_done = true;

    Socket host_socket;
    DataOutputStream dOut;

    MainCharacter character1 = new MainCharacter();
    MainCharacter character2 = new MainCharacter();
    BlankFragment myFragment = new BlankFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

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

        openFragment();

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
                        //send_wifi("hit");
                    }
                    else {
                        //  Used to be get hit, but now ¯\_(ツ)_/¯
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


        MyClientTask myClientTask = new MyClientTask(ipadd,Integer.parseInt(socket));
        myClientTask.execute();

        Log.i("tag wifi", p2response);
        if(p2response.equals("hit")){
            character1.attack(character2);
            if(character2.life <= 0){
                character2.setLife(0);
                imv_p2.animate().rotationY(1080).setDuration(2000);
            }
            txv_lifep2.setText(character2.getLife() + "");
        }

        return true;
    }

    @Override
    protected void onResume(){
        super.onResume();

        receiveFragData();
        Log.i("tag  receivedfrag","onresume");

    }

    private void openFragment(){

        getSupportFragmentManager().beginTransaction().add(R.id.frm_layout,myFragment).commit();

    }

    private void receiveFragData(){
        Intent i = getIntent();
        ipadd = i.getStringExtra("frag_ipadd");
        socket = i.getStringExtra("frag_socket");

        if(ipadd == null)
            ipadd = "localhost";

        if(socket == null)
            socket = String.valueOf(6789);
        else
            getSupportFragmentManager().beginTransaction().remove(myFragment).commit();

        Log.i("tag receivedfrag ","ip: " + ipadd);
        Log.i("tag receivedfrag ","socket: " + socket);


    }

    public void send_wifi(String message){

        try {
            host_socket = new Socket(ipadd, Integer.parseInt(socket));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dOut = new DataOutputStream(host_socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dOut.writeByte(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dOut.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public class MyClientTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;
        String response;

        MyClientTask(String addr, int port){
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                Socket socket = new Socket(dstAddress, dstPort);
                InputStream inputStream = socket.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream =
                        new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];

                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }

                socket.close();
                response = byteArrayOutputStream.toString("UTF-8");

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            p2response = response + "";
            super.onPostExecute(result);
        }

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
