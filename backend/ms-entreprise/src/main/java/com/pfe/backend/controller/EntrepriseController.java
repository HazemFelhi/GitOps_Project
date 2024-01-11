


package com.pfe.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.backend.exception.EntrepriseNotFoundException;
import com.pfe.backend.exception.EntrepriseProcessingException;
import com.pfe.backend.feign.Document;
import com.pfe.backend.feign.DocumentFeignClient;
import com.pfe.backend.feign.Employee;
import com.pfe.backend.feign.EmployeeFeignClient;
import com.pfe.backend.feign.Invitation;
import com.pfe.backend.feign.InvitationFeignClient;
import com.pfe.backend.model.Entreprise;
import com.pfe.backend.service.EntrepriseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/entreprises")
//@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Entreprise")
public class EntrepriseController {
    private static final Logger log = LoggerFactory.getLogger(EntrepriseController.class);

    @Autowired
    private EntrepriseService entrepriseService;
    private final InvitationFeignClient invitationFeignClient;
    private final EmployeeFeignClient employeeFeignClient;
    private final DocumentFeignClient documentFeignClient;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService, InvitationFeignClient invitationFeignClient,
                                EmployeeFeignClient employeeFeignClient, DocumentFeignClient documentFeignClient) {
        this.entrepriseService = entrepriseService;
        this.invitationFeignClient = invitationFeignClient;
        this.employeeFeignClient = employeeFeignClient;
        this.documentFeignClient = documentFeignClient;
    }
    @Operation(summary = "Cette méthode permet de créer une entreprise ")

    @PostMapping
    public ResponseEntity<Entreprise> createEntreprise(@RequestBody Entreprise entreprise) {
        try {
            Entreprise createdEntreprise = entrepriseService.createEntreprise(entreprise);
            return ResponseEntity.ok(createdEntreprise);
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors de l'enregistrement de l'entreprise : ", e);
            throw new EntrepriseProcessingException("Une erreur s'est produite lors de l'enregistrement de l'entreprise.");
        }
    }
    @Operation(summary = "Cette méthode permet de récupérer la totalité des entreprises")

    @GetMapping
    public ResponseEntity<List<Entreprise>> getAllEntreprises() {
        try {
            List<Entreprise> allEntreprises = entrepriseService.getAllEntreprises();
            return ResponseEntity.ok(allEntreprises);
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors de la récupération des entreprises : ", e);
            throw new EntrepriseProcessingException("Une erreur s'est produite lors de la récupération des entreprises.");
        }
    }
    @Operation(summary = "Cette méthode permet de récupérer une entreprise par son id")

    @GetMapping("/{id}")
    public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable Long id) {
        try {
            Entreprise entreprise = entrepriseService.getEntrepriseById(id);
            if (entreprise == null) {
                throw new EntrepriseNotFoundException("L'entreprise avec l'ID " + id + " n'a pas été trouvée.");
            }
            return ResponseEntity.ok(entreprise);
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors de la récupération de l'entreprise : ", e);
            throw new EntrepriseProcessingException("Une erreur s'est produite lors de la récupération de l'entreprise.");
        }
    }
    @Operation(summary = "Cette méthode permet de récupérer une entreprise par son nom ")

    @GetMapping("/name/{name}")
    public ResponseEntity<Entreprise> getEntrepriseByName(@PathVariable String name) {
        try {
            Entreprise entreprise = entrepriseService.getEntrepriseByName(name);
            if (entreprise == null) {
                throw new EntrepriseNotFoundException("L'entreprise avec le nom " + name + " n'a pas été trouvée.");
            }
            return ResponseEntity.ok(entreprise);
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors de la récupération de l'entreprise : ", e);
            throw new EntrepriseProcessingException("Une erreur s'est produite lors de la récupération de l'entreprise.");
        }
    }
    @Operation(summary = "Cette méthode permet de supprimer une entreprise par son id ")

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntreprise(@PathVariable Long id) {
        try {
            String result = entrepriseService.deleteEntreprise(id);
            if (result == null) {
                throw new EntrepriseNotFoundException("L'entreprise avec l'ID " + id + " n'a pas été trouvée.");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors de la suppression de l'entreprise : ", e);
            throw new EntrepriseProcessingException("Une erreur s'est produite lors de la suppression de l'entreprise.");
        }
    }
    @Operation(summary = "Cette méthode permet de modifier les données d'une entreprise ")

    @PutMapping
    public ResponseEntity<Entreprise> updateEntreprise(@RequestBody Entreprise entreprise) {
        try {
            Entreprise updatedEntreprise = entrepriseService.updateEntreprise(entreprise);
            return ResponseEntity.ok(updatedEntreprise);
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors de la mise à jour de l'entreprise : ", e);
            throw new EntrepriseProcessingException("Une erreur s'est produite lors de la mise à jour de l'entreprise.");
        }
    }
    @Operation(summary = "Cette méthode permet d'envoyer une requete au microservice invitation si la personne qui veut rejoindre l'Ese appartient au domaine de l'Ese et il a entré un id valide ")

    @PostMapping("/sendRequest/{entrepriseId}")
    public ResponseEntity<String> sendRequest(@PathVariable("entrepriseId") Long entrepriseId,
                                              @RequestBody Invitation invitation) {
        try {
            String message = entrepriseService.sendRequest(entrepriseId, invitation);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors de l'appel de la méthode sendRequest : ", e);
            throw new EntrepriseProcessingException("Une erreur s'est produite lors de l'appel de la méthode sendRequest.");
        }
    }
    @Operation(summary = "Cette méthode permet de récupérer les employes par l'id de l'entreprise ")

    @GetMapping("/{entrepriseId}/employes")
    public List<Employee> getEmployesByEntreprise(@PathVariable("entrepriseId") Long entrepriseId) {
        return employeeFeignClient.getEmployesByEntreprise(entrepriseId);
    }
    @Operation(summary = "Cette méthode permet de récupérer les documents appartenant à une entreprise (par son id) ")

    @GetMapping("/{entrepriseId}/documents")
    public List<Document> getDocumentsByEntreprise(@PathVariable("entrepriseId") Long entrepriseId) {
        return documentFeignClient.getDocumentsByEntreprise(entrepriseId);
    }
    @Operation(summary = "Cette méthode permet de récupérer les invitations appartenant à une entreprise (par son id) ")

    @GetMapping("/{entrepriseId}/invitations")
    public List<Invitation> getInvitationsByEntreprise(@PathVariable("entrepriseId") Long entrepriseId) {
        return invitationFeignClient.getInvitationsByEntreprise(entrepriseId);
    }

    // Exception handling for EntrepriseNotFoundException
    @ExceptionHandler(EntrepriseNotFoundException.class)
    public ResponseEntity<String> handleEntrepriseNotFoundException(EntrepriseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Exception handling for EntrepriseProcessingException
    @ExceptionHandler(EntrepriseProcessingException.class)
    public ResponseEntity<String> handleEntrepriseProcessingException(EntrepriseProcessingException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}



/* 



    @GetMapping("/{entrepriseId}/check-domain")
    public ResponseEntity<Boolean> checkDomain(@PathVariable("entrepriseId") Long entrepriseId) {
        boolean domainMatch = entrepriseService.checkDomain(entrepriseId);
        return ResponseEntity.ok(domainMatch);
    }
*/

    /*@GetMapping("/{id}/employes")
    public ResponseEntity<List<String>> getEmployeByIdEntreprise(@PathVariable Long id) {
        try {
            List<String> employes = entrepriseService.getEmployeByIdEntreprise(id);
            return ResponseEntity.ok(employes);
        } catch (Exception e) {
            String errorMessage = "Une erreur s'est produite lors de la récupération des employés de l'entreprise : "
                    + e.getMessage();
            System.out.println(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/

   /*  @PostMapping("/invitation")
    public ResponseEntity<String> createInvitation(@RequestBody Long entrepriseId) {
        try {
            String message = entrepriseService.sendRequest(entrepriseId);
            if (message.equals("Un post a été envoyé au microservice invitation")) {
                InvitationFeignClient.createInvitation(entrepriseId);
            }
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            String errorMessage = "Une erreur s'est produite lors de la création de l'invitation : " + e.getMessage();
            System.out.println(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    } */
