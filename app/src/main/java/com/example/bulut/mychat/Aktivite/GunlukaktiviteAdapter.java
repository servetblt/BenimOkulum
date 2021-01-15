package com.example.bulut.mychat.Aktivite;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.example.bulut.mychat.R;
import java.util.List;
public class GunlukaktiviteAdapter extends RecyclerView.Adapter<GunlukaktiviteAdapter.CardTasarimTutucu> {
                private Context context;
                private List<Gunlukaktiviteler> duyurularList;
                private int position;
                public GunlukaktiviteAdapter(Context context, List<Gunlukaktiviteler> duyurularList) {
        this.context = context;
        this.duyurularList = duyurularList;
        }
    @Override
    public GunlukaktiviteAdapter.CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_aktivite,parent,false);
        return new GunlukaktiviteAdapter.CardTasarimTutucu(view) ;
    }
    @Override
    public void onBindViewHolder(final GunlukaktiviteAdapter.CardTasarimTutucu holder, int position) {
        final Gunlukaktiviteler kisi=duyurularList.get(position);
        holder.textViewUser.setText(kisi.getBaslik());
        holder.textViewPass.setText(kisi.getIcerik());
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
