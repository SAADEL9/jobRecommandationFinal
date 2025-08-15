package com.saad.jobRec.repositories;
import java.util.List;
import java.util.Optional;

import com.saad.jobRec.entities.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;

import com.saad.jobRec.entities.Application;


public interface ApplicationRepository extends JpaRepository<Application , Long> {
    List<Application> findByCandidatId(Long candidatId);
    List<Application> findByJobOfferId(Long jobOfferId);
    List<Application> findByJobOfferRecruiterId(Long recruiterId);
    Optional<Application> findApplicationById(Long applicartionId);
}