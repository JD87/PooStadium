package com.example.david.poostadium;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

EditText etx_socket, etx_ipadd;
Button btn_connect;


    public BlankFragment() {

        // Required empty public constructor
    }

    public void senddata(){
        Intent i = new Intent(getActivity().getBaseContext(), WifiActivity.class);
        i.putExtra("frag_ipadd", etx_ipadd.getText().toString());
        i.putExtra("frag_socket", etx_socket.getText().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        etx_ipadd = view.findViewById(R.id.etx_ipadd);
        etx_socket = view.findViewById(R.id.etx_socket);
        btn_connect = view.findViewById(R.id.btn_connect);

        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "yup, btn down",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
