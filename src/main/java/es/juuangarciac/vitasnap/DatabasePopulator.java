package es.juuangarciac.vitasnap;

import es.juuangarciac.vitasnap.user.domain.Role;
import es.juuangarciac.vitasnap.user.domain.User;
import es.juuangarciac.vitasnap.user.services.UserManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabasePopulator implements CommandLineRunner {

    private UserManagementService userManagementService;

    @Autowired
    public DatabasePopulator(UserManagementService userManagementService){
        this.userManagementService = userManagementService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userManagementService.count() == 0) {
            /*Admin */
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            user.setEmail("admin@gmail.es");
            user.addRole(Role.ADMIN);
            userManagementService.registerUser(user);
            userManagementService.activateUser(user.getEmail(), user.getRegisterCode());
            System.out.println("Admin created");
        }
    }

}
