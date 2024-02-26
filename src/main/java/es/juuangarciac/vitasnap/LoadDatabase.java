package es.juuangarciac.vitasnap;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.juuangarciac.vitasnap.user.domain.Role;
import es.juuangarciac.vitasnap.user.domain.User;
import es.juuangarciac.vitasnap.user.repositories.UserRepository;
import es.juuangarciac.vitasnap.security.SecurityConfiguration;

@Configuration
class LoadDatabase {
  private final PasswordEncoder passwordEncoder = SecurityConfiguration.passwordEncoder();

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Autowired
  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {

    if(!repository.findAll().isEmpty()) return args -> {};

        User admin = new User();
          admin.setUsername("admin");
          admin.setPassword(passwordEncoder.encode("admin"));
          admin.setEmail("admin@gmail.com");
          admin.setRegisterCode(UUID.randomUUID().toString().substring(0, 5));
          admin.addRole(Role.ADMIN);
          admin.setActive(true);
        
        User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setEmail(user.getUsername() + "@gmail.com");
            user.setRegisterCode(UUID.randomUUID().toString().substring(0, 5));
            user.addRole(Role.USER);
            user.setActive(true);

    return args -> {
      log.info("Preloading " + repository.save(admin));
      log.info("Preloading " + repository.save(user));
    };
  }
}
