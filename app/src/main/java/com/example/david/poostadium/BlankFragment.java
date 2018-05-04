package com.example.david.poostadium;


import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import static android.content.Context.WIFI_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends DialogFragment {

EditText etx_socket, etx_ipadd;
TextView txv_frag;
Button btn_connect;


    public BlankFragment() {

        // Required empty public constructor
    }

    public void senddata(){
        Intent i = new Intent(getActivity().getBaseContext(), WifiActivity.class);
        i.putExtra("frag_ipadd", etx_ipadd.getText().toString());
        i.putExtra("frag_socket", etx_socket.getText().toString());
        startActivity(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        etx_ipadd = view.findViewById(R.id.etx_ipadd);
        etx_socket = view.findViewById(R.id.etx_socket);
        btn_connect = view.findViewById(R.id.btn_connect);
        txv_frag = view.findViewById(R.id.txv_frag);

        WifiManager wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());

        txv_frag.setText(ipAddress);

        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senddata();
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
