package es.juuangarciac.vitasnap.user.repositories;

import es.juuangarciac.vitasnap.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    List<User> findByActiveTrue();

    Optional<User> findByUsername(String username);

}