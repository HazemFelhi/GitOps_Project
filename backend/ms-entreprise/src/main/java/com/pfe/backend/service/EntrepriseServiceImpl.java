package com.pfe.backend.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;

import com.pfe.backend.feign.Invitation;
import com.pfe.backend.feign.InvitationFeignClient;
/*import com.pfe.backend.model.Employee;*/
import com.pfe.backend.model.Entreprise;
import com.pfe.backend.repository.EntrepriseRepository;

import lombok.extern.slf4j.Slf4j;

/*import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
*/
@Service
/*@Slf4j */
public class EntrepriseServiceImpl implements EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private InvitationFeignClient invitationFeignClient; // Injection du Feign client

    @Override
    public Entreprise createEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public List<Entreprise> getAllEntreprises() {
        return entrepriseRepository.findAll();
    }

    @Override
    public Entreprise getEntrepriseById(Long id) {
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(id);
        return optionalEntreprise.orElse(null);
    }

    @Override
    public String deleteEntreprise(Long id) {
        Optional<Entreprise> entrepriseOptional = entrepriseRepository.findById(id);
        if (entrepriseOptional.isPresent()) {
            entrepriseRepository.deleteById(id);
            return "Entreprise removed: " + id;
        } else {
            return "L'entreprise avec cet id n'existe pas";
        }
    }

    @Override
    public Entreprise getEntrepriseByName(String name) {
        return entrepriseRepository.findByNomEntreprise(name);
    }

    @Override
    public boolean doesEntrepriseExist(Long id) {
        return false;
    }

    @Override
    public boolean checkDomain(Long entrepriseId) {
        return false;
    }

    @Override
    public String sendRequest(Long entrepriseId, Invitation invitation) {
        return null;
    }

    @Override
    public Entreprise updateEntreprise(Entreprise entreprise) {
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(entreprise.getId());
        if (optionalEntreprise.isPresent()) {
            Entreprise existingEntreprise = optionalEntreprise.get();
            existingEntreprise.setNomEntreprise(entreprise.getNomEntreprise());
            existingEntreprise.setNombreEmploye(entreprise.getNombreEmploye());
            existingEntreprise.setPays(entreprise.getPays());
            existingEntreprise.setVille(entreprise.getVille());
            existingEntreprise.setNumero(entreprise.getNumero());
            existingEntreprise.setEmailEntreprise(entreprise.getEmailEntreprise());
            existingEntreprise.setSecteurActivites(entreprise.getSecteurActivites());
            existingEntreprise.setCodePostal(entreprise.getCodePostal());
            existingEntreprise.setFixe(entreprise.getFixe());
            return entrepriseRepository.save(existingEntreprise);
        } else {
            return null;
        }
    }
/*
    @Override
    public boolean doesEntrepriseExist(Long id) {
        return entrepriseRepository.existsById(id);
    }

	@Override
public boolean checkDomain(Long entrepriseId) {
    JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    org.springframework.security.oauth2.jwt.Jwt jwt = authentication.getToken();

    String userEmail = (String) jwt.getClaims().get("email");
    Entreprise entreprise = entrepriseRepository.findById(entrepriseId).orElse(null);
    if (entreprise == null) {
        return false; // L'entreprise n'existe pas
    }

    String userDomain = getUserDomain(userEmail);
    String entrepriseDomain = getDomainFromEmail(entreprise.getEmailEntreprise());

    return userDomain.equalsIgnoreCase(entrepriseDomain);
}

    private String getUserDomain(String userEmail) {
        // Récupérer le domaine de l'utilisateur à partir de son email
        int atIndex = userEmail.lastIndexOf("@");
        if (atIndex != -1 && atIndex < userEmail.length() - 1) {
            return userEmail.substring(atIndex + 1);
        }
        return "";
    }

    private String getDomainFromEmail(String email) {
        // Récupérer le domaine à partir de l'email de l'entreprise
        int atIndex = email.lastIndexOf("@");
        if (atIndex != -1 && atIndex < email.length() - 1) {
            return email.substring(atIndex + 1);
        }
        return "";
    }

    @Override
    public String sendRequest(Long entrepriseId, Invitation invitation) {
        boolean domainMatch = checkDomain(entrepriseId);
        boolean entrepriseExists = doesEntrepriseExist(entrepriseId);

        if (domainMatch && entrepriseExists) {
          /*   InvitationFeignClient.createInvitation(invitation);*/
            /*return "Un post a été envoyé au microservice invitation";
        } else {
            return "Non, pas de correspondance ";
        }
    }*/

    /*@Override
    public List<String> getEmployeByIdEntreprise(Long id) {
        Entreprise entreprise = entrepriseRepository.findById(id).orElse(null);
        if (entreprise != null) {
            return entreprise.getListeEmployes();
        } else {
            return Collections.emptyList();
        }
    }*/

}
