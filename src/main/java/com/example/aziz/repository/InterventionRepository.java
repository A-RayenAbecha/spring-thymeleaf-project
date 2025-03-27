package com.example.aziz.repository;

import com.example.aziz.entity.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    Optional<Intervention> findByClientId(Long clientId);
}
