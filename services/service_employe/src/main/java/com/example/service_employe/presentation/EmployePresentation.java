package com.example.service_employe.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.service_employe.application.Employe;
import com.example.service_employe.application.EmployeService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("employes")
public class EmployePresentation {
    //permet à Spring de récupérer l'instance EmployeService afin de manipuler son contenu
    @Autowired
    private EmployeService service;

    //verbe de création
    @POST
    //permet de dire que le webservice attend un json avec la requête
    @Consumes("application/json")
    public void creationEmploye(CreationEmployeDTO employeDTO){
        Employe employeToSave = new EmployeMapper().mapEmployeDTOToEmploye(employeDTO);
        service.creationEmploye(employeToSave);
    }

    @GET
    @Produces("application/json")
    public List<EmployeDTO> getEmployes(@QueryParam("idEmployes") List<Integer> idEmployes){
        //ne pas oublier de mapper les données :)
        EmployeMapper em = new EmployeMapper();
        List<Employe> employesBdd = new ArrayList<>();
        //sélection de la méthode à appeler en fonction des filtres
        if(idEmployes.isEmpty()){
            employesBdd = service.getEmployes();
        }else{
            employesBdd = service.getEmployesByIds(idEmployes);
        }
        List<EmployeDTO> employesRetournes = new ArrayList<>();
        //dans cette partie, on transforme les données en EmployeDTO
        for(Employe e : employesBdd){
            employesRetournes.add(em.mapEmployeToEmployeDTO(e));
        }
        return employesRetournes;
    }
}