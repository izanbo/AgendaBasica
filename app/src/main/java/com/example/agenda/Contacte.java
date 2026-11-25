package com.example.agenda;

import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class Contacte implements Serializable {

    private String nom;
    private String cognom;
    private String adreca;
    private String telefon;
    private String mail;
    private String dataNaixement;

    public Contacte(
            String nom,
            String cognom,
            String adreca,
            String telefon,
            String mail,
            String dataNaixement
    ) {
        this.nom = nom;
        this.cognom = cognom;
        this.adreca = adreca;
        this.telefon = telefon;
        this.mail = mail;
        this.dataNaixement = dataNaixement;
    }

    /**
     * Extracts a {@link Contacte} extra from an {@link Intent} across API levels.
     */
    @Nullable
    public static Contacte fromIntent(@Nullable Intent intent, String key) {
        if (intent == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return intent.getSerializableExtra(key, Contacte.class);
        }
        @SuppressWarnings("deprecation")
        Object extra = intent.getSerializableExtra(key);
        return extra instanceof Contacte ? (Contacte) extra : null;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacte)) return false;
        Contacte contacte = (Contacte) o;
        return Objects.equals(nom, contacte.nom)
                && Objects.equals(cognom, contacte.cognom)
                && Objects.equals(adreca, contacte.adreca)
                && Objects.equals(telefon, contacte.telefon)
                && Objects.equals(mail, contacte.mail)
                && Objects.equals(dataNaixement, contacte.dataNaixement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, cognom, adreca, telefon, mail, dataNaixement);
    }

    @Override
    public String toString() {
        return "Contacte{" +
                "nom='" + nom + '\'' +
                ", cognom='" + cognom + '\'' +
                ", adreca='" + adreca + '\'' +
                ", telefon='" + telefon + '\'' +
                ", mail='" + mail + '\'' +
                ", dataNaixement='" + dataNaixement + '\'' +
                '}';
    }
}
