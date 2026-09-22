package com.example.service_entreprise.presentation;

import java.util.List;

public class CreationEntrepriseDTO {
    private int id;
    private String nom;
    private List<Integer> idEmployes;
    private String imageUrl;
    private String description;
    private String lieu;
    private String domaine;

    public CreationEntrepriseDTO(){}

    public CreationEntrepriseDTO(int id, String nom, List<Integer> idEmployes) {
        this.id = id;
        this.nom = nom;
        this.idEmployes = idEmployes;
    }

    public CreationEntrepriseDTO(int id, String nom, List<Integer> idEmployes, String imageUrl, String description, String lieu, String domaine) {
        this.id = id;
        this.nom = nom;
        this.idEmployes = idEmployes;
        this.imageUrl = imageUrl;
        this.description = description;
        this.lieu = lieu;
        this.domaine = domaine;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLieu(String lieu) {
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Integer> getIdEmployes() {
        return idEmployes;
    }

    public void setIdEmployes(List<Integer> idEmployes) {
        this.idEmployes = idEmployes;
    }
}