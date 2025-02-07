package com.example.projectdam;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetaliiUniversitateActivity extends AppCompatActivity {
    @SuppressLint("StringFormatMatches")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_universitate);

        Universitate universitate = (Universitate) getIntent().getSerializableExtra("universitate");

        TextView numeTextView = findViewById(R.id.numeUniversitate);
        TextView adresaTextView = findViewById(R.id.adresaUniversitate);
        TextView anFondareTextView = findViewById(R.id.anFondareUniversitate);

        numeTextView.setText(getString(R.string.denumire_facultate, universitate.getNume()));
        adresaTextView.setText(getString(R.string.adresa, universitate.getAdresa()));
        anFondareTextView.setText(getString(R.string.an_fondare, universitate.getAnulFondarii()));

        Button butonInapoi = findViewById(R.id.buton_inapoi);
        butonInapoi.setText(R.string.buton_inapoi);
        butonInapoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
