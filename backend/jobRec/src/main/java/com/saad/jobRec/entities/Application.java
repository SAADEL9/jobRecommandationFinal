package com.saad.jobRec.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Table(name = "application")
@Data
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;
    @ManyToOne

    private JobOffer jobOffer;
    private Date applicationDate;
    private String status;
    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }



    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
