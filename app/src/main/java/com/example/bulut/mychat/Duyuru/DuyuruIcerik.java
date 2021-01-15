package com.example.bulut.mychat.Duyuru;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bulut.mychat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DuyuruIcerik extends AppCompatActivity {

    TextView baslik, icerik;
    Button kaydet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyuru_icerik);


        baslik = findViewById(R.id.baslik);
        icerik = findViewById(R.id.icerik);
        kaydet = findViewById(R.id.button);

        //Time();
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Duyuruekle();
                Toast toast = Toast.makeText(getApplicationContext(), "Duyurunuz Başarı İle Oluşturuldu", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(DuyuruIcerik.this, Duyuru.class);
                startActivity(intent);
            }
        });
    }

    private void Duyuruekle() {
        String url = "http://maarifokullarigine.com/servet/duyurular.php";

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

                params.put("baslik", baslik.getText().toString());
                params.put("icerik", icerik.getText().toString());

                return params;

            }
        };

        Volley.newRequestQueue(this).add(istek);
    }
/*
    private void DuyuruKontrol() {


        String url = "http://maarifokullarigine.com/servet/DuyuruBil.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("cevap", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray kisilerliste = jsonObject.getJSONArray("tblDuyuruKontrol");
                    for (int i = 0; i < kisilerliste.length(); i++) {
                        JSONObject jsonItem = kisilerliste.getJSONObject(i);

                      baslik.setText(jsonItem.getString("baslik"));
                      icerik.setText(jsonItem.getString("icerik"));
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        Volley.newRequestQueue(DuyuruIcerik.this).add(istek);
    }
    private void bildirimGoster() {

        NotificationCompat.Builder builder;

        NotificationManager bildirimYoneticisi =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent ıntent = new Intent(this, DuyuruIcerik.class);

        PendingIntent gidilecekIntent = PendingIntent.getActivity(this, 1, ıntent, PendingIntent.FLAG_UPDATE_CURRENT);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Oreo sürümü için bu kod çalışacak.

                String kanalId = "kanalId";
                String kanalAd = "kanalAd";
                String kanalTanım = "kanalTanım";
                int kanalOnceligi = NotificationManager.IMPORTANCE_HIGH;

                NotificationChannel kanal = bildirimYoneticisi.getNotificationChannel(kanalId);

                if (kanal == null) {
                    kanal = new NotificationChannel(kanalId, kanalAd, kanalOnceligi);
                    kanal.setDescription(kanalTanım);
                    bildirimYoneticisi.createNotificationChannel(kanal);
                }

                builder = new NotificationCompat.Builder(this, kanalId);


                    builder.setContentTitle(baslik.getText().toString())  // gerekli
                            .setContentText(icerik.getText().toString())  // gerekli
                            .setSmallIcon(R.drawable.resim) // gerekli
                            .setAutoCancel(true)  // Bildirim tıklandıktan sonra kaybolur."
                            .setContentIntent(gidilecekIntent);

            } else { // OREO Sürümü haricinde bu kod çalışacak.

                builder = new NotificationCompat.Builder(this);

                    builder.setContentTitle(baslik.getText().toString())  // gerekli
                            .setContentText(icerik.getText().toString())  // gerekli
                            .setSmallIcon(R.drawable.resim) // gerekli
                            .setContentIntent(gidilecekIntent)
                            .setAutoCancel(true)  // Bildirim tıklandıktan sonra kaybolur."
                            .setPriority(Notification.PRIORITY_HIGH);

            }

        bildirimYoneticisi.notify(1, builder.build());
        baslik.setText("");
        icerik.setText("");
    }
    private void Time() {

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                DuyuruKontrol();
                bildirimGoster();
                Duyurusil();
                Time();
            }
        }.start();
    }
    private void Duyurusil() {
        String url = "http://maarifokullarigine.com/servet/DuyuruSil.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("cevap", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray kisilerliste = jsonObject.getJSONArray("tblDuyuruKontrol");
                    for (int i = 0; i < kisilerliste.length(); i++) {
                        JSONObject jsonItem = kisilerliste.getJSONObject(i);
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        Volley.newRequestQueue(DuyuruIcerik.this).add(istek);
    }
    */
}