package ma.bank.repositories;

import ma.bank.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}
