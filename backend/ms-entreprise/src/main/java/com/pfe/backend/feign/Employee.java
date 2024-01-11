package com.pfe.backend.feign;

import java.util.List;

public class Employee {
    private Long id;
    public void setId(Long id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setEntrepriseId(Long entrepriseId) {
        this.entrepriseId = entrepriseId;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
    public void setServicesCloud(List<String> servicesCloud) {
        this.servicesCloud = servicesCloud;
    }
    public Long getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public Long getEntrepriseId() {
        return entrepriseId;
    }
    public String getEmail() {
        return email;
    }
    public String getNumTel() {
        return numTel;
    }
    public List<String> getServicesCloud() {
        return servicesCloud;
    }
    private String nom;
    private String prenom;
    private Long entrepriseId;
    private String email;
    private String numTel;
    private List<String> servicesCloud;
}
