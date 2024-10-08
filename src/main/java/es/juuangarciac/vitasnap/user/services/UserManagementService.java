package es.juuangarciac.vitasnap.user.services;

import es.juuangarciac.vitasnap.user.domain.Role;
import es.juuangarciac.vitasnap.user.domain.User;
import es.juuangarciac.vitasnap.user.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserManagementService implements UserDetailsService {

    private final UserRepository repository;

    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public UserManagementService(UserRepository repository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }


    public boolean registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegisterCode(UUID.randomUUID().toString().substring(0, 5));
        user.addRole(Role.USER);

        try {
            emailService.sendRegistrationEmail(user);
            repository.save(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    
    public boolean registerTestUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegisterCode(UUID.randomUUID().toString().substring(0, 5));
        user.addRole(Role.USER);

        try {
            repository.save(user);
            
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public Optional<User> updateUser(User newUser, String id) {
        Optional<User> user = repository.findById(UUID.fromString(id));
        if(user.isPresent()) {
            user.get().setUsername(newUser.getUsername());
            user.get().setEmail(newUser.getEmail());
            //TO-DO
            return user;
        }else{
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            return user.get();
        }
    }

    public boolean activateUser(String email, String registerCode) {

        Optional<User> user = repository.findByEmail(email);

        if (user.isPresent() && user.get().getRegisterCode().equals(registerCode)) {
            user.get().setActive(true);
            repository.save(user.get());
            return true;

        } else {
            return false;
        }

    }

    public boolean existUsername(String username){
        if(repository.findByUsername(username).isPresent()) return true;
        else    return false;
    }

    public Optional<User> loadUserById(UUID userId) {
        return repository.findById(userId);
    }

    public List<User> loadActiveUsers() {
        return repository.findByActiveTrue();
    }

    public void delete(UUID userId) {
        repository.deleteById(userId);
    }

    public int count() {
        return (int) repository.count();
    }
}
