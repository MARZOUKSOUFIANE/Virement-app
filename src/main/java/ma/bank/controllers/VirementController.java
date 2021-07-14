package ma.bank.controllers;
import ma.bank.entities.Virement;
import ma.bank.repositories.VirementRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VirementController {

    private final VirementRepository repository;

    public VirementController(VirementRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/Virement")
    List<Virement> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/Virement")
    Virement newVirement(@RequestBody Virement newVirement) {
        return repository.save(newVirement);
    }

    // Single item

    @GetMapping("/Virement/{id}")
    Virement one(@PathVariable Long id) throws VirementNotFoundException {

        return repository.findById(id)
                .orElseThrow(() -> new VirementNotFoundException(id));
    }

    @PutMapping("/Virement/{id}")
    Virement replaceVirement(@RequestBody Virement newVirement, @PathVariable Long id) {

        return repository.findById(id)
                .map(Virement -> {
                    Virement.setBeneficierId(newVirement.getBeneficierId());
                    Virement.setCompteId(newVirement.getCompteId());
                    Virement.setMontant(newVirement.getMontant());
                    Virement.setMotif(newVirement.getMotif());

                    return repository.save(Virement);
                })
                .orElseGet(() -> {
                    newVirement.setId(id);
                    return repository.save(newVirement);
                });
    }

    @DeleteMapping("/Virement/{id}")
    void deleteVirement(@PathVariable Long id) {
        repository.deleteById(id);

    }

    private static class VirementNotFoundException extends Exception {
        public VirementNotFoundException(Long id) {
            System.out.println("Virement " + id + " not found");
        }
    }
}
