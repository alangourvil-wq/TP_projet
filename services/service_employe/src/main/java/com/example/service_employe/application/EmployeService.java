package com.example.service_employe.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service_employe.infrastructure.EmployeRepository;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.*;

//permet à Spring d'initialiser cet objet et de le configurer
@Service
public class EmployeService {
    //permet de récupérer l'instance de EmployeRepository dans Spring et de manipuler JPA à travers cette dernière
    @Autowired
    private EmployeRepository repo;

    public List<Employe> getEmployes(){
        return repo.findAll();//retourne tous les employes contenues en bdd
    }

    public void creationEmploye(Employe employe){
        repo.save(employe);
    }

    public List<Employe> getEmployesByIds(List<Integer> idEmployes){
        List<Employe> employesRetournes = new ArrayList<>();
        //parcoure tous les identifiants reçus et va chercher les infos employés un par un
        //ce n'est pas la méthode la plus optimisée mais c'est une solution simple
        for(int id : idEmployes){
            Optional<Employe> e = repo.findById(id);
            //ce controle permet d'ajouter les infos employé seulement si elles existent
            if(e.isPresent()){
                employesRetournes.add(e.get());
            }
        }
        return employesRetournes;
    }

    public List<EmployeDAO> getEmployes(List<Integer> idEmployes){
        //déclaration de l'objet permettant de déclencher un appel REST
        Client client = ClientBuilder.newClient();
        //on paramètre le lien d'appel, notre module Employe
        WebTarget target = client.target("http://localhost:8081/api/employes");
        // Ajout des query params représentant les identifiants d'employés
        for (Integer id : idEmployes) {
            target = target.queryParam("idEmployes", id);
        }
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        //ici, cela nous permet de convertir directement la réponse du webservice en objet EmployeDAO
        List<EmployeDAO> employes = response.readEntity(new GenericType<List<EmployeDAO>>() {});
        response.close();
        client.close();
        return employes;
    }
}