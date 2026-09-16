package com.example.service_entreprise.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.service_entreprise.application.EmployeDAO;
import com.example.service_entreprise.application.Entreprise;
import com.example.service_entreprise.application.EntrepriseService;
import com.example.service_entreprise.application.NoteService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("entreprises")
public class EntreprisePresentation {

    @Autowired
    private EntrepriseService service;

    @Autowired
    private NoteService noteService;

    @GET
    @Produces("application/json")
    public List<EntrepriseDTO> getEntreprises(){
        EntrepriseMapper em = new EntrepriseMapper();
        List<Entreprise> entreprisesBdd = service.getEntreprises();
        List<EntrepriseDTO> entreprisesRetournees = new ArrayList<>();

        for(Entreprise e : entreprisesBdd){
            EntrepriseDTO entrepriseAAjouter = em.mapEntrepriseToEntrepriseDTO(e);

            if(e.getIdEmployes() != null && !e.getIdEmployes().isEmpty()){
                List<EmployeDAO> employes = service.getEmployes(e.getIdEmployes());
                entrepriseAAjouter.setEmployes(em.mapEmployeDAOToEmployeDTO(employes));
            }

            // ajout de la moyenne de note pour l'affichage dans le listing
            entrepriseAAjouter.setMoyenneNote(noteService.getMoyenne(e.getId()));

            entreprisesRetournees.add(entrepriseAAjouter);
        }
        return entreprisesRetournees;
    }

    // endpoint de détail, utilisé par la page détail de l'entreprise
    @GET
    @Path("{id}")
    @Produces("application/json")
    public EntrepriseDTO getEntreprise(@PathParam("id") int id){
        EntrepriseMapper em = new EntrepriseMapper();
        Entreprise e = service.getEntrepriseById(id);
        EntrepriseDTO dto = em.mapEntrepriseToEntrepriseDTO(e);

        if(e.getIdEmployes() != null && !e.getIdEmployes().isEmpty()){
            List<EmployeDAO> employes = service.getEmployes(e.getIdEmployes());
            dto.setEmployes(em.mapEmployeDAOToEmployeDTO(employes));
        }

        dto.setMoyenneNote(noteService.getMoyenne(id));
        return dto;
    }

    @POST
    @Consumes("application/json")
    public void creationEntreprise(CreationEntrepriseDTO entrepriseDTO){
        Entreprise entrepriseToSave = new EntrepriseMapper().mapEntrepriseDTOToEntreprise(entrepriseDTO);
        service.creationEntreprise(entrepriseToSave);
    }

    @POST
    @Path("{id}/notes")
    @Consumes("application/json")
    public void noterEntreprise(@PathParam("id") int id, NoteDTO noteDTO){
        noteService.ajouterNote(id, noteDTO.getValeur());
    }
}