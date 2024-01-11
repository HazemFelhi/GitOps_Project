package  com.pfe.backend.repository ;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.backend.model.Employee;
@Repository

public interface EmployeeRepository extends JpaRepository <Employee , Long>  {
   /*méthode spécifique  */ List<Employee> findByEntrepriseId(Long entrepriseId);

}
