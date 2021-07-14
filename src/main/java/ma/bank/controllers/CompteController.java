package ma.bank.controllers;

import ma.bank.entities.Compte;
import ma.bank.repositories.CompteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompteController {

    private final CompteRepository repository;


    public CompteController(CompteRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/Compte")
    List<Compte> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/Compte")
    Compte newCompte(@RequestBody Compte newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item

    @GetMapping("/Compte/{id}")
    Compte one(@PathVariable Long id) throws CompteNotFoundException {

        return repository.findById(id)
                .orElseThrow(() -> new CompteNotFoundException(id));
    }

    @PutMapping("/Compte/{id}")
    Compte replaceEmployee(@RequestBody Compte newCompte, @PathVariable Long id) {

        return repository.findById(id)
                .map(Compte -> {
                    Compte.setCNumero(newCompte.getCNumero());
                    Compte.setCodeBanque(newCompte.getCodeBanque());
                    Compte.setCSolde(newCompte.getCSolde());
                    return repository.save(Compte);
                })
                .orElseGet(() -> {
                    newCompte.setId(id);
                    return repository.save(newCompte);
                });
    }

    @DeleteMapping("/Compte/{id}")
    void deleteCompte(@PathVariable Long id) {
        repository.deleteById(id);

    }
    public static class CompteNotFoundException extends Exception  {
        public CompteNotFoundException(Long id) {
            System.out.println("Compte " + id + " Not Found ");
        }
    }
    }
