package com.pfe.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.backend.model.Invitation;


@Repository
public interface  InvitationRepository extends JpaRepository <Invitation, Long> {

    List<Invitation> findByEntrepriseId(Long entrepriseId);
} 

