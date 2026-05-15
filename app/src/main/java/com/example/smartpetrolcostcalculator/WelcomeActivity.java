package com.example.smartpetrolcostcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sembunyikan Action Bar atas khas untuk Welcome Page supaya nampak penuh/cinematic
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_welcome);

        // Sambungkan butang dari XML
        MaterialButton btnGetStarted = findViewById(R.id.btnGetStarted);

        // Fungsi apabila butang ditekan
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke MainActivity (Kalkulator)
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);

                // Tutup WelcomeActivity supaya bila tekan 'Back' tak kembali ke sini
                finish();
            }
        });
    }
}