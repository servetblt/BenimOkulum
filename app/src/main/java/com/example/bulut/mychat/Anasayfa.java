package com.example.bulut.mychat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.bulut.mychat.Aktivite.Gunlukaktivite;
import com.example.bulut.mychat.Duyuru.Duyuru;
import com.example.bulut.mychat.Galeri.Galeri;
import com.example.bulut.mychat.Yemek.Yemek;
import com.example.bulut.mychat.Yoklama.Yoklama;

public class Anasayfa extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageButton message, duyuru, galeri, yoklama, yemek, aktivite, izin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        message = findViewById(R.id.imageMessage);
        duyuru = findViewById(R.id.imageDuyuru);
        galeri = findViewById(R.id.imageGaleri);
        yoklama = findViewById(R.id.imageYoklama);
        yemek = findViewById(R.id.imageYemek);
        aktivite = findViewById(R.id.imageAktivite);
        izin = findViewById(R.id.imageIzin);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Anasayfa.this, MainActivity.class);
                startActivity(intent);

            }
        });
        duyuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Anasayfa.this, Duyuru.class);
                startActivity(intent);
            }
        });

        galeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Anasayfa.this, Galeri.class);
                startActivity(intent);
            }
        });

        yoklama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Anasayfa.this, Yoklama.class);
                startActivity(intent);
            }
        });
        yemek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Anasayfa.this, Yemek.class);
                startActivity(intent);
            }
        });
        aktivite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Anasayfa.this, Gunlukaktivite.class);
                startActivity(intent);
            }
        });
        izin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Anasayfa.this, Anasayfa.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.anasayfa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(Anasayfa.this, Anasayfa.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String shareBody = "http://www.turcomente.com";
            String shareSub = "Mobil Ana Okul Ailesine katılın ";
            myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(myIntent, "Paylaş"));


        } else if (id == R.id.nav_send) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(Anasayfa.this);
            builder.setMessage("Çıkış Yapmak Üzeresiniz");
            builder.setCancelable(true);
            builder.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setPositiveButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}