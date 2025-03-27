package com.example.aziz.service;

import com.example.aziz.entity.Client;
import com.example.aziz.entity.Intervention;
import com.example.aziz.repository.InterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class InterventionService {

    @Autowired
    private InterventionRepository interventionRepository;

    public void saveOrUpdateIntervention(Intervention newIntervention, Long clientId, Client client) {
        Optional<Intervention> existingInterventionOpt = interventionRepository.findByClientId(clientId);

        if (existingInterventionOpt.isPresent()) {
            Intervention existingIntervention = existingInterventionOpt.get();
            existingIntervention.setSalle(newIntervention.getSalle());
            existingIntervention.setDate(LocalDate.now());
            existingIntervention.setHeure(LocalTime.now());// Allow updating salle only
            interventionRepository.save(existingIntervention);
        } else {
            newIntervention.setClient(client);
            interventionRepository.save(newIntervention);
        }
    }


    public Optional<Intervention> getInterventionByClientId(Long clientId) {
        return interventionRepository.findByClientId(clientId);
    }
    public Intervention getInterventionById(Long id) {
        return interventionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Intervention not found with id: " + id));
    }

    public void updateIntervention(Intervention intervention) {
        interventionRepository.save(intervention);
    }
}
