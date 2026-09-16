package com.example.service_entreprise.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.service_entreprise.application.EmployeDAO;
import com.example.service_entreprise.application.Entreprise;
import com.example.service_entreprise.application.EntrepriseService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("entreprises")
public class EntreprisePresentation {
    //permet à Spring de récupérer l'instance EntrepriseService afin de manipuler son contenu
    @Autowired
    private EntrepriseService service;

    @GET
    @Produces("application/json")
    public List<EntrepriseDTO> getEntreprises(){
        //ne pas oublier de mapper les données :)
        EntrepriseMapper em = new EntrepriseMapper();
        //on récupère toutes les entreprises
        List<Entreprise> entreprisesBdd = service.getEntreprises();
        //cette liste nous sert d'objet de retour
        List<EntrepriseDTO> entreprisesRetournees = new ArrayList<>();
        //dans cette partie, on transforme les données en EntrepriseDTO
        for(Entreprise e : entreprisesBdd){
            EntrepriseDTO entrepriseAAjouter = new EntrepriseDTO();
            entrepriseAAjouter = em.mapEntrepriseToEntrepriseDTO(e);
            //partie Employes, on va interroger le module Employe
            //et mettre à dispo les infos employés dans chaque entreprise
            if(e.getIdEmployes() != null && !e.getIdEmployes().isEmpty()){
                //ici onfait l'appel au module Employe via la couche application
                List<EmployeDAO> employes = service.getEmployes(e.getIdEmployes());
                //on map les employés dans l'objet de retour associé et on l'ajoute à son entreprise
                entrepriseAAjouter.setEmployes(em.mapEmployeDAOToEmployeDTO(employes));
            }
            //on ajoute l'entreprise mappée dans le résultat de la requête
            entreprisesRetournees.add(entrepriseAAjouter);
        }
        return entreprisesRetournees;
    }

    //verbe de création
    @POST
    //permet de dire que le webservice attend un json avec la requête
    @Consumes("application/json")
    public void creationEntreprise(CreationEntrepriseDTO entrepriseDTO){
        Entreprise entrepriseToSave = new EntrepriseMapper().mapEntrepriseDTOToEntreprise(entrepriseDTO);
        service.creationEntreprise(entrepriseToSave);
    }
}
