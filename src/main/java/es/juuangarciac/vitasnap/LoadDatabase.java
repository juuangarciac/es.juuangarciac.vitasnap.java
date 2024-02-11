package es.juuangarciac.vitasnap;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.juuangarciac.vitasnap.user.domain.Role;
import es.juuangarciac.vitasnap.user.domain.User;
import es.juuangarciac.vitasnap.user.repositories.UserRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {
        User admin = new User();
          admin.setUsername("admin");
          admin.setPassword("admin");
          admin.setEmail("admin@gmail.com");
          admin.setRegisterCode(UUID.randomUUID().toString().substring(0, 5));
          admin.addRole(Role.ADMIN);
        
        User user = new User();
            user.setUsername("user");
            user.setPassword("user");
            user.setEmail(user.getUsername() + "@gmail.com");
            user.setRegisterCode(UUID.randomUUID().toString().substring(0, 5));
            user.addRole(Role.USER);

    return args -> {
      log.info("Preloading " + repository.save(admin));
      log.info("Preloading " + repository.save(user));
    };
  }
}
