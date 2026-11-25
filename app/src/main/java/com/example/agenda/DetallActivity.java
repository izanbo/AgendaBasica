package com.example.agenda;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetallActivity extends AppCompatActivity {

    private int posicio = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall);

        TextView tvTitol = findViewById(R.id.tvTitolDetall);
        EditText etNom = findViewById(R.id.etNom);
        EditText etCognom = findViewById(R.id.etCognom);
        EditText etAdreca = findViewById(R.id.etAdreca);
        EditText etTelefon = findViewById(R.id.etTelefon);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etDataNaixement = findViewById(R.id.etDataNaixement);

        ImageButton btnTrucar = findViewById(R.id.btnDetallTrucar);
        ImageButton btnEmail = findViewById(R.id.btnDetallEmail);
        Button btnAcceptar = findViewById(R.id.btnAcceptar);
        Button btnCancellar = findViewById(R.id.btnCancellar);
        Button btnEsborrar = findViewById(R.id.btnEsborrar);

        Intent intent = getIntent();
        posicio = intent.getIntExtra("POSICIO", -1);
        Contacte contacteRebut = Contacte.fromIntent(intent, "CONTACTE");

        if (contacteRebut != null && posicio != -1) {
            tvTitol.setText(getString(R.string.title_edit));
            etNom.setText(contacteRebut.getNom());
            etCognom.setText(contacteRebut.getCognom());
            etAdreca.setText(contacteRebut.getAdreca());
            etTelefon.setText(contacteRebut.getTelefon());
            etEmail.setText(contacteRebut.getMail());
            etDataNaixement.setText(contacteRebut.getDataNaixement());
            btnEsborrar.setVisibility(View.VISIBLE);
        } else {
            tvTitol.setText(getString(R.string.title_new));
            btnEsborrar.setVisibility(View.GONE);
        }

        btnTrucar.setOnClickListener(v -> {
            String telefon = etTelefon.getText().toString().trim();
            if (!telefon.isEmpty()) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telefon)));
            }
        });

        btnEmail.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            if (!email.isEmpty()) {
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email)));
            }
        });

        btnAcceptar.setOnClickListener(v -> {
            String nom = etNom.getText().toString().trim();
            String cognom = etCognom.getText().toString().trim();
            String adreca = etAdreca.getText().toString().trim();
            String telefon = etTelefon.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String dataNaixement = etDataNaixement.getText().toString().trim();

            if (nom.isEmpty() || telefon.isEmpty()) {
                Toast.makeText(this, getString(R.string.msg_required), Toast.LENGTH_SHORT).show();
            } else {
                Contacte nouContacte = new Contacte(nom, cognom, adreca, telefon, email, dataNaixement);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("CONTACTE", nouContacte);
                resultIntent.putExtra("POSICIO", posicio);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        btnEsborrar.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("POSICIO", posicio);
            resultIntent.putExtra("ESBORRAR", true);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });

        btnCancellar.setOnClickListener(v -> {
            setResult(Activity.RESULT_CANCELED);
            finish();
        });
    }
}
