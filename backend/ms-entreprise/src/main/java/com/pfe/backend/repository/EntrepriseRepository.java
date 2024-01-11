package  com.pfe.backend.repository ;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.backend.model.Entreprise;

@Repository
public interface EntrepriseRepository  extends JpaRepository <Entreprise, Long> {
    Entreprise findByNomEntreprise(String nomEntreprise);
}