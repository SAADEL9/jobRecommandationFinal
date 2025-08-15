package com.saad.jobRec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saad.jobRec.entities.Application;
import com.saad.jobRec.services.ApplicationService;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:5173")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<Application> applyToOffer(
            @RequestParam Long offerId,
            @RequestParam Long candidatId) {
        Application saved = applicationService.applyToJob(offerId, candidatId);
        return ResponseEntity.ok(saved);
    }
    @PreAuthorize("hasRole('RECRUITER')")
    @GetMapping("/by-recruiter/{recruiterId}")
    public ResponseEntity<List<Application>> getByRecruiter(@PathVariable Long recruiterId) {

        System.out.println("application by recruiter");
        return ResponseEntity.ok(applicationService.getApplicationsForRecruiter(recruiterId));
    }

    @GetMapping("/by-candidat/{candidatId}")
    @PreAuthorize("hasRole('CANDIDAT')")
    public ResponseEntity<List<Application>> getByCandidat(@PathVariable Long candidatId) {
        System.out.println("application by candidat");
        return ResponseEntity.ok(applicationService.getApplicationsForCandidat(candidatId));
    }
    @PutMapping("/{applicationId}/status")
    public ResponseEntity<Application> updateStatus(
            @PathVariable Long applicationId,
            @RequestParam String newStatus) {
        Application application = applicationService.updateStatus(applicationId, newStatus);
        return ResponseEntity.ok(application);
    }
}
