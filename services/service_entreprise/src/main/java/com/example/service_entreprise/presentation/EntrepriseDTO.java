package com.example.service_entreprise.presentation;

import java.util.List;

import com.example.service_entreprise.application.EmployeDTO;

public class EntrepriseDTO {
    private int id;
    private String nom;
    private List<EmployeDTO> employes;
    private double moyenneNote;

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

    public double getMoyenneNote() { return moyenneNote; }
    public void setMoyenneNote(double moyenneNote) { this.moyenneNote = moyenneNote; }
}