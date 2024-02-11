package es.juuangarciac.vitasnap.user.controllers;

public class UserNotFoundException extends RuntimeException {

    UserNotFoundException(Long id) {
        super("Could not find user " + id);
      }
}
