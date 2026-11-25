package com.example.agenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvContactes;
    private ContacteAdapter adapter;
    private final List<Contacte> llistaContactes = new ArrayList<>();

    private final ActivityResultLauncher<Intent> detallLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    int posicio = data.getIntExtra("POSICIO", -1);
                    boolean esborrar = data.getBooleanExtra("ESBORRAR", false);

                    if (esborrar && posicio != -1) {
                        llistaContactes.remove(posicio);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, getString(R.string.msg_deleted), Toast.LENGTH_SHORT).show();
                    } else {
                        Contacte contacte = Contacte.fromIntent(data, "CONTACTE");

                        if (contacte != null) {
                            if (!contacte.getTelefon().startsWith("+34")) {
                                contacte.setTelefon("+34 " + contacte.getTelefon());
                            }

                            if (posicio == -1) {
                                llistaContactes.add(contacte);
                            } else {
                                llistaContactes.set(posicio, contacte);
                            }
                            adapter.notifyDataSetChanged();
                            Toast.makeText(this, getString(R.string.msg_saved), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llistaContactes.add(new Contacte("Pol", "Giménez", "Carrer Major 12", "+34 653151285", "e.pgimenezm@ies-sabadell.cat", "24/11/2006"));
        llistaContactes.add(new Contacte("Izan", "Boyero", "Avinguda Roma 45", "+34 644305430", "izanbo@ies-sabadell.cat", "02/05/2005"));
        llistaContactes.add(new Contacte("Adam", "Partida", "Carrer Menor 13", "+34 622133180", "apartidam@ies-sabadell.cat", "19/08/2000"));
        llistaContactes.add(new Contacte("Victoria", "Alcázar", "Avinguda Paris 25", "+34 653124380", "e.valcazarf@ies-sabadell.cat", "09/07/2007"));
        llistaContactes.add(new Contacte("Didac", "Avellán", "Carrer Mitja 14", "+34 611413670", "davellana@ies-sabadell.cat", "26/09/2007"));
        llistaContactes.add(new Contacte("Adrian", "Herrera", "Avinguda Cadiz 15", "+34 647883871", "aherrerag@ies-sabadell.cat", "14/07/2001"));

        lvContactes = findViewById(R.id.lvContactes);
        Button btnNouContacte = findViewById(R.id.btnNouContacte);

        adapter = new ContacteAdapter(this, llistaContactes);
        lvContactes.setAdapter(adapter);

        btnNouContacte.setOnClickListener(v -> {
            Intent intent = new Intent(this, DetallActivity.class);
            detallLauncher.launch(intent);
        });

        lvContactes.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, DetallActivity.class);
            intent.putExtra("CONTACTE", llistaContactes.get(position));
            intent.putExtra("POSICIO", position);
            detallLauncher.launch(intent);
        });
    }
}
