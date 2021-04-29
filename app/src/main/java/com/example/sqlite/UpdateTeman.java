package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class UpdateTeman extends AppCompatActivity {
    private TextInputEditText tiNama, tiTelpon;
    private Button editBtn;
    String id, nama, telpon;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_data);

        tiNama = (TextInputEditText)findViewById(R.id.UpdateNama);
        tiTelpon = (TextInputEditText)findViewById(R.id.UpdateTelpon);
        editBtn = (Button)findViewById(R.id.buttonEdit);

        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra("nama");
        telpon = getIntent().getStringExtra("telpon");

        setTitle("Update Data");
        tiNama.setText(nama);
        tiTelpon.setText(telpon);


        editBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (tiNama.getText().toString().equals("")||tiTelpon.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Data Belum Terisi Semua !", Toast.LENGTH_SHORT).show();
                }else{
                    nama = tiNama.getText().toString();
                    telpon = tiTelpon.getText().toString();
                    HashMap<String,String> qvalues = new HashMap<>();
                    qvalues.put("id",id);
                    qvalues.put("nama",nama);
                    qvalues.put("telpon",telpon);
                    controller.updateData(qvalues);
                    callHome();
                }
            }
        });
    }

    public void callHome(){
        Intent intent = new Intent(UpdateTeman.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
