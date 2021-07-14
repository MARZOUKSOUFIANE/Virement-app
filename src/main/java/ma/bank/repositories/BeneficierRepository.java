package ma.bank.repositories;

import ma.bank.entities.Beneficier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficierRepository extends JpaRepository<Beneficier, Long> {


}
