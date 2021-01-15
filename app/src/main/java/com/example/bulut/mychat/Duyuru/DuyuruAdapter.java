package com.example.bulut.mychat.Duyuru;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.bulut.mychat.R;

import java.util.List;

public class DuyuruAdapter extends RecyclerView.Adapter<DuyuruAdapter.CardTasarimTutucu> {

    private Context context;
    private List<Duyurular> duyurularList;

    public DuyuruAdapter(Context context, List<Duyurular> duyurularList) {
        this.context = context;
        this.duyurularList = duyurularList;
    }

    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_duyuru,parent,false);

        return new CardTasarimTutucu(view) ;
    }

    @Override
    public void onBindViewHolder(CardTasarimTutucu holder, int position) {

        final Duyurular kisi=duyurularList.get(position);

        holder.textViewUser.setText(kisi.getBaslik());
        holder.textViewPass.setText(kisi.getIcerik());

        holder.kisiCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,DuyuruDetay.class);

                intent.putExtra("nesne",kisi);

                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return duyurularList.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{

        private TextView textViewUser,textViewPass;
        private CardView kisiCard;

        public CardTasarimTutucu(View itemView) {
            super(itemView);

            kisiCard=itemView.findViewById(R.id.kisiler_card);
            textViewUser=itemView.findViewById(R.id.user);
            textViewPass=itemView.findViewById(R.id.pass);
        }
    }
}
