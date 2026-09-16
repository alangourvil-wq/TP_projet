package com.example.service_entreprise.presentation;

import java.util.ArrayList;
import java.util.List;

import com.example.service_entreprise.application.EmployeDAO;
import com.example.service_entreprise.application.EmployeDTO;
import com.example.service_entreprise.application.Entreprise;

public class EntrepriseMapper {
    public EntrepriseDTO mapEntrepriseToEntrepriseDTO(Entreprise entreprise){
        return new EntrepriseDTO(entreprise.getId(), entreprise.getNom());
    }

    public Entreprise mapEntrepriseDTOToEntreprise(CreationEntrepriseDTO entrepriseDTO){
        return new Entreprise(entrepriseDTO.getId(), entrepriseDTO.getNom(),entrepriseDTO.getIdEmployes());
    }

	public List<EmployeDTO> mapEmployeDAOToEmployeDTO(List<EmployeDAO> employes) {
		List<EmployeDTO> employesDTO = new ArrayList<>();
		for (EmployeDAO employe : employes) {
			employesDTO.add(new EmployeDTO(employe.getId(), employe.getNom()));
		}
		return employesDTO;
	}
}
