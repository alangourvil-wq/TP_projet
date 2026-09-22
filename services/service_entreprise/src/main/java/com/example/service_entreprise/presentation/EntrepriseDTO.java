package com.example.service_entreprise.presentation;

import java.util.List;

import com.example.service_entreprise.application.EmployeDTO;

public class EntrepriseDTO {
    private int id;
    private String nom;
    private List<EmployeDTO> employes;
    private String imageUrl;
    private String description;
    private String lieu;
    private String domaine;


    public EntrepriseDTO() {}

    public EntrepriseDTO(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public EntrepriseDTO(int id, String nom, List<EmployeDTO> employes) {
        this.id = id;
        this.nom = nom;
        this.employes = employes;
    }

    public EntrepriseDTO(int id, String nom, List<EmployeDTO> employes, String imageUrl, String description, String lieu, String domaine) {
        this.id = id;
        this.nom = nom;
        this.employes = employes;
        this.imageUrl = imageUrl;
        this.description = description;
        this.domaine = domaine;
        this.lieu = lieu;
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

    public List<EmployeDTO> getEmployes() {
        return employes;
    }

    public void setEmployes(List<EmployeDTO> employes) {
        this.employes = employes;
    }
}