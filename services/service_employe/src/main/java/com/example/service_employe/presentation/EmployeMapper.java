package com.example.service_employe.presentation;

import com.example.service_employe.application.Employe;

public class EmployeMapper {
    public EmployeDTO mapEmployeToEmployeDTO(Employe employe){
        return new EmployeDTO(employe.getId(), employe.getNom());
    }

    public Employe mapEmployeDTOToEmploye(CreationEmployeDTO employeDTO){
        return new Employe(employeDTO.getId(), employeDTO.getNom());
    }
}