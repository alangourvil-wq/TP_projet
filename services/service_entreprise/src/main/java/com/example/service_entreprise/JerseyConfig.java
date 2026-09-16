package com.example.service_entreprise;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.example.service_entreprise.presentation.EntreprisePresentation;

import jakarta.ws.rs.ApplicationPath;

//permet de configurer l'instanciation avec Spring
@Component
//donne un chemin de base aux liens qui vont être exposés
@ApplicationPath("api")
//indique à Spring que le composant qui va être créé qu'il s'agit d'une configuration globale
@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        //enregistre la classe où les webservices vont être exposés
        register(EntreprisePresentation.class);
        register(CorsResponseFilter.class);
    }
}
