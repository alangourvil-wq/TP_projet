package com.example.service_entreprise.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service_entreprise.infrastructure.NoteRepository;

@Service
public class NoteService {

    @Autowired
    private NoteRepository repo;

    public void ajouterNote(int idEntreprise, int valeur){
        if (valeur < 1 || valeur > 5) {
            throw new IllegalArgumentException("La note doit être comprise entre 1 et 5");
        }
        repo.save(new Note(idEntreprise, valeur));
    }

    public double getMoyenne(long idEntreprise){
  
        List<Note> notes = repo.findByIdEntreprise(idEntreprise);
        if (notes.isEmpty()) {
            return 0.0;
        }
        double somme = 0;
        for (Note n : notes) {
            somme += n.getValeur();
        }
        return somme / notes.size();
    }
}