package com.example.smartpetrolcostcalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Sambungkan butang dari XML
        MaterialButton btnGithub = findViewById(R.id.btnGithub);

        // Fungsi apabila butang ditekan
        btnGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 🔴 TUKAR URL DI DALAM PEMBUKA KATA "" INI KEPADA URL GITHUB AWAK YANG SEBENAR:
                String githubUrl = "https://github.com/farhanaazreen0406-rgd2/smart-petrol-cost-calculator";
                // Intent untuk membuka web browser luar
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(githubUrl));
                startActivity(intent);
            }
        });
    }

    // --- KOD MENU TIGA TITIK (NAVIGASI) ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Kembali ke halaman Utama (Kalkulator)
            Intent intent = new Intent(AboutActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.nav_faq) {
            // Pergi ke halaman FAQ
            Intent intent = new Intent(AboutActivity.this, FaqActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.nav_about) {
            // Sudah berada di halaman About
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}