package com.example.service_employe.application;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private List<Integer> idEmployes;

    public Employe(){}

    public Employe(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Employe(int id, String nom, List<Integer> idEmployes) {
        this.id = id;
        this.nom = nom;
        this.idEmployes = idEmployes;
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
