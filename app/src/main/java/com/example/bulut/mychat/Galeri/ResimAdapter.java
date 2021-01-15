package com.example.bulut.mychat.Galeri;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.bulut.mychat.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ResimAdapter extends RecyclerView.Adapter<ResimAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Resimler> resimlerList;

    public ResimAdapter(Context mContext, List<Resimler> resimlerList) {
        this.mContext = mContext;
        this.resimlerList = resimlerList;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private CardView resim_card;
        private TextView textViewResimAd;
        private ImageView imageViewFilmResim;
        public CardTasarimTutucu(View itemView) {
            super(itemView);
            resim_card = itemView.findViewById(R.id.resim_card);
            textViewResimAd = itemView.findViewById(R.id.caption);
            imageViewFilmResim = itemView.findViewById(R.id.imageViewResim);
        }
    }

    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resim_card_tasarimi,parent,false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(CardTasarimTutucu holder, int position) {
        final Resimler resim = resimlerList.get(position);

        holder.textViewResimAd.setText(resim.getCaption());

        String url = "http://maarifokullarigine.com/servet/resim/"+resim.getPhoto_url();

        Picasso.get().load(url).into(holder.imageViewFilmResim);

        holder.resim_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });

    }

    @Override
    public int getItemCount() {
        return resimlerList.size();
    }




}
