package com.kumalalatif.kirimpesan.myAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kumalalatif.kirimpesan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 03/11/2017.
 */


public class myMessageAdapter extends RecyclerView.Adapter<myMessageAdapter.MAdapter> {

    JSONArray jsonArray;

    public myMessageAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public MAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_spinner,parent,false);
        return new MAdapter(view);
    }

    @Override
    public void onBindViewHolder(MAdapter holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.fotoku.setImageResource(jsonObject.getInt("Foto"));
            holder.namaku.setText(jsonObject.getString("Nama Pengirim"));
            holder.pesanku.setText(jsonObject.getString("Isi Pesan"));
            holder.tanggalku.setText(jsonObject.getString("Waktu Kirim"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class MAdapter extends RecyclerView.ViewHolder {
        ImageView fotoku;
        TextView namaku, pesanku, tanggalku;
        public MAdapter(View itemView) {
            super(itemView);
            fotoku = (ImageView) itemView.findViewById(R.id.gambarCardView);
            namaku = (TextView) itemView.findViewById(R.id.namaCardView);
            pesanku = (TextView) itemView.findViewById(R.id.pesanCardView);
            tanggalku = (TextView) itemView.findViewById(R.id.tanggalCardView);
        }
    }
}