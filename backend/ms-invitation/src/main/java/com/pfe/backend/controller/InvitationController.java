package com.pfe.backend.controller;

import com.pfe.backend.feign.EmployeFeignClient;
import com.pfe.backend.feign.Employee;
import com.pfe.backend.model.Invitation;
import com.pfe.backend.service.InvitationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invitations")
@SecurityRequirement(name = "bearerAuth")
@Tag(name="Invitation")
public class InvitationController {
    private final InvitationService invitationService;
    private final EmployeFeignClient employeFeignClient;

    @Autowired
    public InvitationController(InvitationService invitationService, EmployeFeignClient employeFeignClient) {
        this.invitationService = invitationService;
        this.employeFeignClient = employeFeignClient;
    }

    @PostMapping
    public Invitation createInvitation(@RequestBody Invitation invitation) {
        try {
            return invitationService.createInvitation(invitation);
        } catch (Exception e) {
            String errorMessage = "Une erreur s'est produite lors de l'enregistrement de l'invitation : " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }
/* 
    @Override
    public Invitation updateInvitation(Invitation invitation) {
        return invitationRepository.save(invitation);
    }
*/
    @GetMapping
    public List<Invitation> getAllInvitations() {
        try {
            return invitationService.getAllInvitations();
        } catch (Exception e) {
            String errorMessage = "Une erreur s'est produite lors de la récupération des invitations : " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }

    @GetMapping("/{id}")
    public Invitation getInvitationById(@PathVariable Long id) {
        try {
            return invitationService.getInvitationById(id);
        } catch (Exception e) {
            String errorMessage = "Une erreur s'est produite lors de la récupération de l'invitation : " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String rejectInvitation(@PathVariable Long id) {
        try {
            return invitationService.rejectInvitation(id);
        } catch (Exception e) {
            String errorMessage = "Une erreur s'est produite lors de la suppression de l'invitation : " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<String> acceptInvitation(@PathVariable Long id) {
        try {
            Invitation invitation = invitationService.getInvitationById(id);
            if (invitation == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invitation not found.");
            }
    
            invitationService.acceptInvitation(id);
    
            // Créez un objet Employe avec les données de l'invitation
            Employee nouvelEmploye = new Employee();
            nouvelEmploye.setId(invitation.getId());
            nouvelEmploye.setEmail(invitation.getEmail());
            nouvelEmploye.setEntrepriseId(invitation.getEntrepriseId());
    
            ResponseEntity<String> response = employeFeignClient.addEmployee(nouvelEmploye);
            if (response.getStatusCode() == HttpStatus.CREATED) {
                // L'employé a été ajouté avec succès dans "ms-employe"
                return ResponseEntity.ok("L'invitation a été acceptée et l'employé a été ajouté.");
            } else {
                // Gérez les erreurs ou les cas d'échec de l'ajout de l'employé
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'employé.");
            }
        } catch (Exception e) {
            String errorMessage = "Une erreur s'est produite lors de l'acceptation de l'invitation : " + e.getMessage();
            System.out.println(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
        @GetMapping("/inv/{entrepriseId}")
    public List<Invitation> getInvitationsByEntreprise(@PathVariable("entrepriseId") Long entrepriseId) {
        return invitationService.getInvitationsByEntreprise(entrepriseId);
    }
}
