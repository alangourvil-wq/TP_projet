package com.example.service_employe.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.service_employe.application.Employe;

//permet à Spring de créer l'objet et de le configurer correctement
@Component
//ici on indique à JPARepository qu'on manipule des objets Employe
//et ces derniers possèdent un identifiant en format entier
public interface EmployeRepository extends JpaRepository<Employe, Integer>{

}