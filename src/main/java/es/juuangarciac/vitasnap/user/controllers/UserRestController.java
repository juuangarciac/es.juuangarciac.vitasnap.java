package es.juuangarciac.vitasnap.user.controllers;

import es.juuangarciac.vitasnap.user.domain.User;
import es.juuangarciac.vitasnap.user.services.UserManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
/**
 * 'UserRestController'. Permite responder peticiones HTTP que se hagan a vitasnap.
 */
@RestController
public class UserRestController {

    @Autowired
    private final UserManagementService service;

    public UserRestController(UserManagementService service) {
        this.service = service;
    }

    @GetMapping("/api/users")
    public List<User> all() {
        return service.loadActiveUsers();
    }

    @PostMapping("/api/users")
    void newUser(@RequestBody User newUser) {
        service.registerUser(newUser);
    }

    // Single item

    @GetMapping("/api/users/{id}")
    EntityModel<User> one(@PathVariable String id) {
        // TODO deal with invalid UUID
        User user = service.loadUserById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException(id));
        
        return EntityModel.of(user, 
            linkTo(methodOn(UserRestController.class).one(id)).withSelfRel(),
            linkTo(methodOn(UserRestController.class).all).withSelfRel("users"),
        )
    }

    @PutMapping("/api/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable String id) {
        return service.updateUser(newUser, id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/api/users/{id}")
    void deleteUser(@PathVariable Integer id) {
        // TODO
    }
}
