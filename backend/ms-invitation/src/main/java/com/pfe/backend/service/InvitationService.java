package com.pfe.backend.service;

import com.pfe.backend.model.Invitation;

import java.util.List;

public interface InvitationService {
    Invitation createInvitation(Invitation invitation);

  /*   List<Invitation> saveInvitations(List<Invitation> invitations); */

    Invitation getInvitationById(Long id);

    List<Invitation> getAllInvitations();
   /*  Invitation updateInvitation(Invitation invitation); */

    String rejectInvitation(Long id);

    void acceptInvitation(Long id);
    List<Invitation> getInvitationsByEntreprise(Long entrepriseId) ;

}
