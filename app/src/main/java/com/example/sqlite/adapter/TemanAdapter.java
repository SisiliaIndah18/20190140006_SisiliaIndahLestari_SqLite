package com.example.sqlite.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.LihatTeman;
import com.example.sqlite.MainActivity;
import com.example.sqlite.R;
import com.example.sqlite.UpdateTeman;
import com.example.sqlite.database.DBController;
import com.example.sqlite.database.Teman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder>{
    private ArrayList<Teman> listData;
    private Context context;

    public TemanAdapter(ArrayList<Teman> listData){
        this.listData= listData;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String id, nama, telpon;

        id = listData.get(position).getId();
        nama = listData.get(position).getNama();
        telpon = listData.get(position).getTelpon();
        DBController controller = new DBController(context);

        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(nama);
        holder.telponTxt.setText(telpon);

        holder.cardku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context, LihatTeman.class);
                i.putExtra("id", id);
                i.putExtra("nama", nama);
                i.putExtra("telpon", telpon);
                context.startActivity(i);
            }
        });

        holder.cardku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(context, holder.cardku);
                popup.inflate(R.menu.popup_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.update:
                                Intent i= new Intent(context, UpdateTeman.class);
                                i.putExtra("id", id);
                                i.putExtra("nm", nama);
                                i.putExtra("tlp", telpon);
                                context.startActivity(i);
                                break;
                            case R.id.delete:
                                HashMap<String,String> qvalues = new HashMap<>();
                                qvalues.put("id", id);
                                controller.deleteData(qvalues);
                                Intent in = new Intent(context, MainActivity.class);
                                context.startActivity(in);
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return (listData != null)?listData.size() :0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView namaTxt, telponTxt;
        public TemanViewHolder(View view){
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);

        }
    }
}
