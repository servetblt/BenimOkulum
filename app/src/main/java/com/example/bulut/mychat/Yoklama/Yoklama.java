package com.example.bulut.mychat.Yoklama;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bulut.mychat.Anasayfa;
import com.example.bulut.mychat.Duyuru.Duyuru;
import com.example.bulut.mychat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Yoklama extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    MyCustomAdapter dataAdapter = null;
    ArrayList<Yoklamalar> duyuruListele;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoklama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        listView =findViewById(R.id.listView1);
        displayListView();
        checkButtonClick();
        Yoklama();


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
            Intent intent = new Intent(Yoklama.this, Anasayfa.class);
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
            Intent intent = new Intent(Yoklama.this, Anasayfa.class);
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
            final AlertDialog.Builder builder= new AlertDialog.Builder(Yoklama.this);
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


    private void displayListView() {
        ArrayList<Yoklamalar> countryList = new ArrayList<Yoklamalar>();
        dataAdapter = new MyCustomAdapter(this, R.layout.row, countryList);
        ListView listView = findViewById(R.id.listView1);
        listView.setAdapter(dataAdapter);
    }

    private class MyCustomAdapter extends ArrayAdapter<Yoklamalar> {

        private ArrayList<Yoklamalar> countryList;

        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Yoklamalar> countryList) {
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<Yoklamalar>();
            this.countryList.addAll(countryList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.row, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Yoklamalar country = (Yoklamalar) cb.getTag();
                        country.setSelected(cb.isChecked());
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Yoklamalar country = countryList.get(position);
            holder.code.setText( " "+country.getName());
            holder.name.setText(country.getCode());
            holder.name.setChecked(country.isSelected());
            holder.name.setTag(country);

            return convertView;

        }

    }
    private void checkButtonClick() {
        Button myButton = findViewById(R.id.findSelected);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<Yoklamalar> countryList = dataAdapter.countryList;
                for(int i=0;i<countryList.size();i++){
                    Yoklamalar country = countryList.get(i);
                    if(country.isSelected()){


                        Boolean durumm= country.isSelected();
                        String name=country.getCode();
                        String surname=country.getName();

                        YoklamaKomtrol(durumm,name,surname);

                    }
                    else{
                        responseText.append("\n" +country.isSelected()+" "+country.getCode()+" "+country.getName());

                        Boolean durumm= country.isSelected();
                        String name=country.getCode();
                        String surname=country.getName();

                        YoklamaKomtrol(durumm,name,surname);
                    }
                }

                Toast.makeText(getApplicationContext(),
                        "Yoklama Kayıt Edildi.", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void YoklamaKomtrol(Boolean durumm, String name, String surname) {

        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        final String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        final String durum = durumm.toString();
        final String user = name.toString();
        final String pass = surname.toString();

        String url = "http://maarifokullarigine.com/servet/yoklamaDurum.php";

        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("cevap", response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("user", user);
                params.put("pass", pass);
                params.put("durum", durum);
                return params;

            }
        };
        Volley.newRequestQueue(this).add(istek);
    }
    public void Yoklama(){

        String url="http://maarifokullarigine.com/servet/tum_kisiler.php";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                duyuruListele=new ArrayList<>();

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray kisilerliste=jsonObject.getJSONArray("user");

                    for(int i=0; i<kisilerliste.length(); i++){
                        JSONObject d=kisilerliste.getJSONObject(i);

                        String baslik=d.getString("user");
                        String icerik=d.getString("pass");
                        boolean durum=false;


                        Yoklamalar kisi=new Yoklamalar(baslik,icerik,durum);

                        duyuruListele.add(kisi);


                    }
                    dataAdapter=new MyCustomAdapter(Yoklama.this, R.layout.row,duyuruListele);
                    ListView listView2 = findViewById(R.id.listView1);
                    listView2.setAdapter(dataAdapter);

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
