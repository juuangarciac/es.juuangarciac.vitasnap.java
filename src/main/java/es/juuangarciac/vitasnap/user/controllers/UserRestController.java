package es.juuangarciac.vitasnap.user.controllers;

import es.juuangarciac.vitasnap.user.domain.User;
import es.juuangarciac.vitasnap.user.services.UserManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * 'UserRestController'. Permite responder peticiones HTTP que se hagan a vitasnap.
 *  Arquitectura: REST
 */
@RestController
@RequestMapping("/api/users")
@ResponseStatus(HttpStatus.OK)
public class UserRestController {

    @Autowired
    private final UserManagementService service;

    public UserRestController(UserManagementService service) {
        this.service = service;
    }

    @GetMapping
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = service.loadActiveUsers().stream()
            .map(
                user -> EntityModel.of(user,
                    linkTo(methodOn(UserRestController.class).one(user.getId().toString())).withSelfRel(),
                    linkTo(methodOn(UserRestController.class).all()).withRel("users")))
            .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserRestController.class).all()).withSelfRel());
    }        

    @PostMapping(consumes = "application/json", produces = "application/json")
    public void createUser(@RequestBody User newUser) {
        service.registerUser(newUser);
    }

    // Single item

    @GetMapping("/{id}")
    public EntityModel<User> one(@PathVariable String id) {

        User user = new User();
        // Deal with invalid UUID
        try{
            UUID userId = UUID.fromString(id);
            user = service.loadUserById(userId)
                .orElseThrow(() -> new UserNotFoundException(id)); 
            
            return EntityModel.of(user, 
                linkTo(methodOn(UserRestController.class).one(id)).withSelfRel(),
                linkTo(methodOn(UserRestController.class).all()).withRel("users")
            );

        }catch(IllegalArgumentException e){
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid UUID");
            return EntityModel.of(user);
        }
    }

    @PutMapping("/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable String id) {
        try{
            UUID.fromString(id);
            return service.updateUser(newUser, id)
                .orElseThrow(() -> new UserNotFoundException(id));
        }catch(IllegalArgumentException e){
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid UUID");
            return new User();
        }
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable String id) {
        try{
            service.delete(UUID.fromString(id));
        }catch(IllegalArgumentException e){ 
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid UUID");
        }
    }
}
