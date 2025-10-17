package com.refrralApp.refrral.repository;

import com.refrralApp.refrral.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    Optional<?> findByEmail(String email);

    Optional<Candidate> findByIdAndEmail(Long candidateId, String email);
}
