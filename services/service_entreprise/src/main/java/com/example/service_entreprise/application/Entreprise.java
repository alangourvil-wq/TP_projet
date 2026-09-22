package com.example.service_entreprise.application;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private List<Integer> idEmployes;
    private String imageUrl;
    private String description;
    private String lieu;

    public Entreprise(){}

    public Entreprise(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Entreprise(int id, String nom, List<Integer> idEmployes) {
        this.id = id;
        this.nom = nom;
        this.idEmployes = idEmployes;
    }

    public Entreprise(int id, String nom, List<Integer> idEmployes, String imageUrl, String description, String lieu) {
        this.id = id;
        this.nom = nom;
        this.idEmployes = idEmployes;
        this.imageUrl = imageUrl;
        this.description = description;
        this.lieu = lieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public List<Integer> getIdEmployes() {
        return idEmployes;
    }

    public void setIdEmployes(List<Integer> idEmployes) {
        this.idEmployes = idEmployes;
    }
}
