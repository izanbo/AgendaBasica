package com.example.agenda;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContacteAdapter extends ArrayAdapter<Contacte> {

    private final Context context;
    private final List<Contacte> contactes;

    public ContacteAdapter(@NonNull Context context, @NonNull List<Contacte> contactes) {
        super(context, 0, contactes);
        this.context = context;
        this.contactes = contactes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView != null
                ? convertView
                : LayoutInflater.from(context).inflate(R.layout.item_contacte, parent, false);
        Contacte contacte = contactes.get(position);

        TextView tvNom = view.findViewById(R.id.tvItemNom);
        TextView tvTelefon = view.findViewById(R.id.tvItemTelefon);
        TextView tvEmail = view.findViewById(R.id.tvItemEmail);
        ImageButton btnTrucar = view.findViewById(R.id.btnItemTrucar);
        ImageButton btnEmail = view.findViewById(R.id.btnItemEmail);

        tvNom.setText(contacte.getNomComplet());
        tvTelefon.setText(contacte.getTelefon());
        tvEmail.setText(contacte.getMail());

        btnTrucar.setOnClickListener(v ->
                Contacte.launchUriAction(context, Intent.ACTION_DIAL, "tel:", contacte.getTelefon()));

        btnEmail.setOnClickListener(v ->
                Contacte.launchUriAction(context, Intent.ACTION_SENDTO, "mailto:", contacte.getMail()));

        return view;
    }
}
