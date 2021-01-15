package com.example.bulut.mychat.Aktivite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bulut.mychat.Anasayfa;
import com.example.bulut.mychat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Gunlukaktivite extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rv;
    private ArrayList<Gunlukaktiviteler> duyuruListele;
    private GunlukaktiviteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gunlukaktivite);

        tumAktiviteler();
        rv = findViewById(R.id.rv);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
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
        getMenuInflater().inflate(R.menu.anasayfa,menu);
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
            Intent intent = new Intent(Gunlukaktivite.this, Anasayfa.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(Gunlukaktivite.this, Anasayfa.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String shareBody="http://www.turcomente.com";
            String shareSub="Mobil Ana Okul Ailesine katılın ";
            myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(myIntent,"Paylaş"));
        } else if (id == R.id.nav_send) {
            final AlertDialog.Builder builder= new AlertDialog.Builder(Gunlukaktivite.this);
            builder.setMessage("Çıkış Yapmak Üzeresiniz");
            builder.setCancelable(true);
            builder.setNegativeButton("Evet",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog,int which) {
                    finish();
                }
            });
            builder.setPositiveButton("Hayır",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog,int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void tumAktiviteler(){
        String url="http://maarifokullarigine.com/servet/aktivite.php";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                duyuruListele=new ArrayList<>();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray kisilerliste=jsonObject.getJSONArray("tblAktivite");
                    for(int i=0; i<kisilerliste.length(); i++){
                        JSONObject d=kisilerliste.getJSONObject(i);
                        String baslik=d.getString("saat");
                        String icerik=d.getString("etkinlik");
                        Gunlukaktiviteler kisi=new Gunlukaktiviteler(baslik,icerik);
                        duyuruListele.add(kisi);
                    }
                    adapter=new GunlukaktiviteAdapter(Gunlukaktivite.this,duyuruListele);
                    rv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
