package com.example.service_entreprise.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.service_entreprise.application.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
    // Change findByEntrepriseId to findByIdEntreprise
    List<Note> findByIdEntreprise(long idEntreprise);
}