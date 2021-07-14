package ma.bank.controllers;
import ma.bank.entities.Beneficier;
import ma.bank.repositories.BeneficierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BeneficierController {

    private final BeneficierRepository repository;


    BeneficierController(BeneficierRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/Beneficier")
    List<Beneficier> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/Beneficier")
    Beneficier newBeneficier(@RequestBody Beneficier newBeneficier) {
        return repository.save(newBeneficier);
    }

    // Single item

    @GetMapping("/Beneficier/{id}")
    Beneficier one(@PathVariable Long id) throws BeneficierNotFoundException {

        return repository.findById(id)
                .orElseThrow(() -> new BeneficierNotFoundException(id));
    }

    @PutMapping("/Beneficier/{id}")
    Beneficier replaceBeneficier(@RequestBody Beneficier newBeneficier, @PathVariable Long id) {

        return repository.findById(id)
                .map(Beneficier -> {
                    Beneficier.setNom(newBeneficier.getNom());
                    Beneficier.setRib(newBeneficier.getRib());

                    return repository.save(Beneficier);
                })
                .orElseGet(() -> {
                    newBeneficier.setId(id);
                    return repository.save(newBeneficier);
                });
    }

    @DeleteMapping("/Beneficier/{id}")
    void deleteBeneficier(@PathVariable Long id) {
        repository.deleteById(id);

    }
    public static class BeneficierNotFoundException extends Exception {
        public BeneficierNotFoundException(Long id) {

            System.out.println("Beneficier " + id + " not found ");
        }
    }

}
