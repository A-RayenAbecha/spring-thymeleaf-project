package com.example.aziz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false, unique = true)
    private Client client;

    @Min(1)
    @Column(nullable = false, updatable = false) // Ensures `ordre` is only set once
    private Integer ordre;

    @Min(1)
    @Column(nullable = false)
    private int salle;

    private LocalTime heure;
    private LocalDate date;

    @PrePersist
    protected void onCreate() {
        this.heure = LocalTime.now();
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        if (ordre == null || ordre <= 0) {
            throw new IllegalArgumentException("Ordre must be a positive number.");
        }
        if (this.ordre == null) { // Only allow setting it once
            this.ordre = ordre;
        }
    }

    public int getSalle() {
        return salle;
    }

    public void setSalle(int salle) {
        if (salle <= 0) {
            throw new IllegalArgumentException("Salle must be a positive number.");
        }
        this.salle = salle;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
