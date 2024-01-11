package  com.pfe.backend.model ;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "entreprise")
public class Entreprise implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nomEntreprise;
    
    private Integer nombreEmploye;

    private String pays;

    private String ville;

    private Integer numero;

    private String  emailEntreprise ;
    
    private String secteurActivites ;
    private String  adresse ;

    private Integer codePostal;

    private Integer fixe;

   private List<String> listeEmployes; 

}

