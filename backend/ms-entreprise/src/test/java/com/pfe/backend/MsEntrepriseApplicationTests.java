package com.pfe.backend;

import com.pfe.backend.model.Entreprise;
import com.pfe.backend.repository.EntrepriseRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MsEntrepriseApplicationTests {

	@Autowired
    private EntrepriseRepository entrepriseRepository;

    @Test
    public void testSaveEntreprise() {
        Entreprise entreprise = new Entreprise();
        entreprise.setNomEntreprise("le test est bon");
        entreprise.setNombreEmploye(10);

        Entreprise savedEntreprise = entrepriseRepository.save(entreprise);

        Assertions.assertThat(savedEntreprise).isNotNull();
        Assertions.assertThat(savedEntreprise.getId()).isGreaterThan(0L);
    }

    @Test
    public void testGetAllEntreprises() {
        Iterable<Entreprise> entreprises = entrepriseRepository.findAll();
        Assertions.assertThat(entreprises).hasSizeGreaterThan(0);

        for (Entreprise entreprise : entreprises) {
            System.out.println(entreprise);
        }
    }

    @Test
    public void testUpdateEntreprise() {
        Long entrepriseId = 1L;
        Entreprise entreprise = entrepriseRepository.findById(entrepriseId).orElse(null);
        Assertions.assertThat(entreprise).isNotNull();

        entreprise.setNomEntreprise("Updated entreprise");
        entrepriseRepository.save(entreprise);

        Entreprise updatedEntreprise = entrepriseRepository.findById(entrepriseId).orElse(null);
        Assertions.assertThat(updatedEntreprise).isNotNull();
        Assertions.assertThat(updatedEntreprise.getNomEntreprise()).isEqualTo("Updated entreprise");
    }

    @Test
    public void testGetEntrepriseById() {
        Long entrepriseId = 1L;
        Entreprise entreprise = entrepriseRepository.findById(entrepriseId).orElse(null);
        Assertions.assertThat(entreprise).isNotNull();

        System.out.println(entreprise);
    }

    @Test
    public void testDeleteEntreprise() {
        Long entrepriseId = 1L;
        entrepriseRepository.deleteById(entrepriseId);

        Entreprise deletedEntreprise = entrepriseRepository.findById(entrepriseId).orElse(null);
        Assertions.assertThat(deletedEntreprise).isNull();
    }


}
