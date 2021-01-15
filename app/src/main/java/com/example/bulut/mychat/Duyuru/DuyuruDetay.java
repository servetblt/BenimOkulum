package com.example.bulut.mychat.Duyuru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.bulut.mychat.Duyuru.Duyurular;
import com.example.bulut.mychat.R;

public class DuyuruDetay extends AppCompatActivity {
    private TextView textViewUser,textViewPass;
    private Duyurular kisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyuru_detay);



        textViewUser=findViewById(R.id.user);
        textViewPass=findViewById(R.id.pass);

        textViewPass.setMovementMethod(new ScrollingMovementMethod());




        kisi=(Duyurular) getIntent().getSerializableExtra("nesne");

        textViewUser.setText(kisi.getBaslik());
        textViewPass.setText(kisi.getIcerik());

    }
}
