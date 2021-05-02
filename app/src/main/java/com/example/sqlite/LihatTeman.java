package com.example.sqlite;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LihatTeman extends AppCompatActivity {
    static TextView tvnama, tvtelpon;
    String nama, id, telpon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihat_data);

        tvnama = findViewById(R.id.tvNamaKontak);
        tvtelpon = findViewById(R.id.tvNomorTelepon);

        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra("nama");
        telpon = getIntent().getStringExtra("telpon");

        setTitle("Lihat Data");
        tvnama.setText(nama);
        tvtelpon.setText(telpon);
    }
}
