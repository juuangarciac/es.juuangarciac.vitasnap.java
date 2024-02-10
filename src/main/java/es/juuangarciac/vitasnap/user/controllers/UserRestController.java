package es.juuangarciac.vitasnap.user.controllers;

import es.juuangarciac.vitasnap.user.domain.User;
import es.juuangarciac.vitasnap.user.services.UserManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
/**
 * 'UserRestController'. Permite responder peticiones HTTP que se hagan a vitasnap.
 */
@RestController
public class UserRestController {

    private final UserManagementService service;

    @Autowired
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
    User one(@PathVariable String id) {
        // TODO deal with invalid UUID
        return service.loadUserById(UUID.fromString(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    @PutMapping("/api/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable String id) {
        return service.updateUser(newUser, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    @DeleteMapping("/api/users/{id}")
    void deleteUser(@PathVariable Integer id) {
        // TODO
    }
}
