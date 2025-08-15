package com.saad.jobRec.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saad.jobRec.entities.Application;
import com.saad.jobRec.entities.Candidat;
import com.saad.jobRec.entities.JobOffer;
import com.saad.jobRec.entities.Recruiter;
import com.saad.jobRec.repositories.ApplicationRepository;
import com.saad.jobRec.repositories.CandidatRepository;
import com.saad.jobRec.repositories.JobOfferRepository;
import com.saad.jobRec.repositories.RecruiterRepository;

@Service
public class ApplicationService {

    @Autowired
    private JobOfferRepository jobofferRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private RecruiterRepository recruiterRepository;

    public Application applyToJob(Long offerId, Long candidatId)
    {
        Application application=new Application();
        JobOffer offer = jobofferRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new RuntimeException("Candidat not found"));
        application.setApplicationDate(new Date());
        application.setCandidat(candidat);
        application.setJobOffer(offer);
        application.setStatus("pending");
        return applicationRepository.save(application);
    }
    public List<Application> getApplicationsForRecruiter(Long recruiterId )
    {
        Recruiter recruiter=recruiterRepository.findById(recruiterId)
        .orElseThrow(() -> new RuntimeException("Recruiter not found"));
        List<Application> allApplications = new ArrayList<>();
        allApplications.addAll(applicationRepository.findByJobOfferRecruiterId(recruiter.getId()));
        return allApplications;
    }

    public List<Application> getApplicationsForCandidat(Long candidatId) {
        return applicationRepository.findByCandidatId(candidatId);
    }
    public Application updateStatus(Long applicationId , String newStatus)
    {
   Application application = applicationRepository.findById(applicationId)
           .orElseThrow(() -> new RuntimeException("Application not found"));
   application.setStatus(newStatus);
   return applicationRepository.save(application);

    }

}

