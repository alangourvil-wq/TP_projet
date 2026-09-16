package com.example.service_entreprise.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service_entreprise.infrastructure.EntrepriseRepository;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

//permet à Spring d'initialiser cet objet et de le configurer
@Service
public class EntrepriseService {
    //permet de récupérer l'instance de EntrepriseRepository dans Spring et de manipuler JPA à travers cette dernière
    @Autowired
    private EntrepriseRepository repo;

    public List<Entreprise> getEntreprises(){
        return repo.findAll();//retourne toutes les entreprises contenues en bdd
    }

    public void creationEntreprise(Entreprise entreprise){
        repo.save(entreprise);
    }

    public List<EmployeDAO> getEmployes(List<Integer> idEmployes) {
        Client client = ClientBuilder.newClient();
        try {
            WebTarget target = client.target("http://localhost:8081/api/employes");
            for (Integer id : idEmployes) {
                target = target.queryParam("idEmployes", id);
            }

            try (Response response = target.request(MediaType.APPLICATION_JSON).get()) {
                if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
                    throw new IllegalStateException(
                        "Le service Employe a répondu avec le statut HTTP " + response.getStatus()
                    );
                }
                return response.readEntity(new GenericType<List<EmployeDAO>>() {});
            }
        } finally {
            client.close();
        }
    }

    public Entreprise getEntrepriseById(Integer id) {
        return repo.findById(id).orElse(null);
    }

}
