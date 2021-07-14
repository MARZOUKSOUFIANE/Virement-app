package ma.bank.controllers;
import ma.bank.entities.Users;
import ma.bank.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository repository;


    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/User")
    List<Users> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/User")
    Users newUser(@RequestBody Users newUsers) {
        return repository.save(newUsers);
    }

    // Single item

    @GetMapping("/User/{id}")
    Users one(@PathVariable Long id) throws UserNotFoundException {

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/User/{id}")
    Users replaceUser(@RequestBody Users newUsers, @PathVariable Long id) {

        return repository.findById(id)
                .map(Users -> {
                    Users.setLogin(newUsers.getLogin());
                    Users.setPassword(newUsers.getPassword());


                    return repository.save(Users);
                })
                .orElseGet(() -> {
                    newUsers.setId(id);
                    return repository.save(newUsers);
                });
    }

    @DeleteMapping("/User/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);

    }

    private static class UserNotFoundException extends Exception {
        public UserNotFoundException(Long id) {
            System.out.println("User " + id + " not found");
        }
    }
}
