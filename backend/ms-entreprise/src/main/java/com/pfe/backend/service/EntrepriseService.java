package  com.pfe.backend.service ;
import java.util.List;

import com.pfe.backend.feign.Invitation;
/*import com.pfe.backend.model.Employee;*/
import com.pfe.backend.model.Entreprise;






public interface EntrepriseService {
    Entreprise createEntreprise(Entreprise entreprise);
   /*  List<Entreprise> saveEntreprises(List<Entreprise> entreprises); */
    List<Entreprise> getAllEntreprises();
    Entreprise getEntrepriseById(Long id);
    String deleteEntreprise(Long id);
    Entreprise updateEntreprise(Entreprise entreprise);
    Entreprise getEntrepriseByName(String name); // Nouvelle méthode ajoutée
    boolean doesEntrepriseExist(Long id);
    boolean checkDomain( Long entrepriseId);
    String sendRequest(Long entrepriseId, Invitation invitation);
    /*List<String> getEmployeByIdEntreprise(Long id);*/

}
