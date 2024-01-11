package com.pfe.backend.service;

import com.pfe.backend.model.Invitation;
import com.pfe.backend.repository.InvitationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {
    private final InvitationRepository invitationRepository;

    @Autowired
    public InvitationServiceImpl(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @Override
    public Invitation createInvitation(Invitation invitation) {
        return invitationRepository.save(invitation);
    }
/* 
    @Override
    public List<Invitation> saveInvitations(List<Invitation> invitations) {
        return invitationRepository.saveAll(invitations);
    }
  */
    @Override
    public Invitation getInvitationById(Long id) {
        return invitationRepository.findById(id).orElse(null);
    }

   @Override
    public List<Invitation> getAllInvitations() {
        return invitationRepository.findAll();
    }

    @Override
    public String rejectInvitation(Long id) {
        invitationRepository.deleteById(id);
        return "Invitation removed: " + id;

    }
/* 
    @Override
    public Invitation updateInvitation(Invitation invitation) {
        return invitationRepository.save(invitation);
    }
*/

    @Override
    public void acceptInvitation(Long id) {
        Invitation invitation = invitationRepository.findById(id).orElse(null);
        if (invitation != null) {
            invitationRepository.deleteById(id);
            System.out.println("Cette invitation va être ajoutée dans la base de données des employés.");
        }
    }


    @Override
    public List<Invitation> getInvitationsByEntreprise(Long entrepriseId) {
        return invitationRepository.findByEntrepriseId(entrepriseId);
    }



}
