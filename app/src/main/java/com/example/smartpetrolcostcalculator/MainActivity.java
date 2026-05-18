package com.example.smartpetrolcostcalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbRon95;
    private TextInputEditText etPricePerLiter, etFuelUsage;
    private SwitchMaterial switchBudi;
    private CardView cardResult;
    private TextView tvTotalCost, tvBudiRebate, tvTotalSaving;
    private MaterialButton btnShareResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sambungkan pembolehubah dengan ID di XML
        RadioGroup rgPetrolType = findViewById(R.id.rgPetrolType);
        rbRon95 = findViewById(R.id.rbRon95);
        etPricePerLiter = findViewById(R.id.etPricePerLiter);
        etFuelUsage = findViewById(R.id.etFuelUsage);
        switchBudi = findViewById(R.id.switchBudi);
        MaterialButton btnCalculate = findViewById(R.id.btnCalculate);
        cardResult = findViewById(R.id.cardResult);
        tvTotalCost = findViewById(R.id.tvTotalCost);
        tvBudiRebate = findViewById(R.id.tvBudiRebate);
        tvTotalSaving = findViewById(R.id.tvTotalSaving);
        btnShareResult = findViewById(R.id.btnShareResult);

        // Fungsi apabila butang Kira ditekan
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePetrolCost();
            }
        });

        // Fungsi apabila butang Share ditekan (TEKNIK LAB 6.3)
        btnShareResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePetrolResult();
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void calculatePetrolCost() {
        // Ambil nilai dan buang ruang kosong (space) di hujung ayat menggunakan .trim()
        String priceStr = Objects.requireNonNull(etPricePerLiter.getText()).toString().trim();
        String usageStr = Objects.requireNonNull(etFuelUsage.getText()).toString().trim();

        // --- BERMULA KOD VALIDATION BARU ---
        if (priceStr.isEmpty()) {
            etPricePerLiter.setError("Sila masukkan Harga Seliter!");
            etPricePerLiter.requestFocus();
            Toast.makeText(this, "Sila masukkan harga dahulu.", Toast.LENGTH_SHORT).show();
            return; // Hentikan pengiraan jika harga kosong
        }
        else if (usageStr.isEmpty()) {
            etFuelUsage.setError("Sila masukkan Jumlah Liter!");
            etFuelUsage.requestFocus();
            Toast.makeText(this, "Sila masukkan jumlah liter.", Toast.LENGTH_SHORT).show();
            return; // Hentikan pengiraan jika liter kosong
        }
        // --- TAMAT KOD VALIDATION BARU ---

        double pricePerLiter = Double.parseDouble(priceStr);
        double fuelUsage = Double.parseDouble(usageStr);

        double totalPetrolCost = fuelUsage * pricePerLiter;
        double budiRebate = 0.0;

        boolean isRon95 = rbRon95.isChecked();
        boolean isBudiEligible = switchBudi.isChecked();

        if (isRon95 && isBudiEligible) {
            budiRebate = fuelUsage * 1.99;
        }

        double totalSaving = totalPetrolCost - budiRebate;

        tvTotalCost.setText(String.format("Jumlah Kos Petrol: RM %.2f", totalPetrolCost));
        tvBudiRebate.setText(String.format("Rebat BUDI: RM %.2f", budiRebate));
        tvTotalSaving.setText(String.format("Jumlah Penjimatan: RM %.2f", totalSaving));

        cardResult.setVisibility(View.VISIBLE);
    }

    // METHOD IMPLICIT INTENT UNTUK KONGSI KANDUNGAN
    private void sharePetrolResult() {
        String costText = tvTotalCost.getText().toString();
        String rebateText = tvBudiRebate.getText().toString();
        String savingText = tvTotalSaving.getText().toString();

        String shareMessage = "⛽ *RINGKASAN KOS SMART PETROL CALCULATOR* ⛽\n\n" +
                "Hai! Ini adalah hasil pengiraan kos petrol saya:\n" +
                "• " + costText + "\n" +
                "• " + rebateText + "\n" +
                "• " + savingText + "\n\n" +
                "Dikira menggunakan aplikasi Smart Petrol Cost Calculator. Jom semak rebat BUDI MADANI anda!";

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "Kongsi keputusan via");
        startActivity(shareIntent);
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

        if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
            return true;
        } else if (id == R.id.nav_faq) {
            startActivity(new Intent(MainActivity.this, FaqActivity.class));
            return true;
        } else if (id == R.id.nav_home) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}