package com.example.service_entreprise.application;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private long idEntreprise;
    private int valeur; // entre 1 et 5

    public Note(){}

    public Note(long idEntreprise, int valeur) {
        this.idEntreprise = idEntreprise;
        this.valeur = valeur;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public long getIdEntreprise() { return idEntreprise; }
    public void setIdEntreprise(long idEntreprise) { this.idEntreprise = idEntreprise; }

    public int getValeur() { return valeur; }
    public void setValeur(int valeur) { this.valeur = valeur; }
}