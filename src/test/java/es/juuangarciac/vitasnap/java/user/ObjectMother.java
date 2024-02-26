package es.juuangarciac.vitasnap.java.user;

import java.util.UUID;

import com.github.javafaker.Faker;
import es.juuangarciac.vitasnap.user.domain.User;

public class ObjectMother {

    private static final Faker faker = new Faker();


    public static User createTestUser() {
        User testUser = new User();
        testUser.setId(UUID.randomUUID());
        testUser.setUsername(faker.name().username());
        testUser.setEmail(faker.internet().emailAddress());
        testUser.setPassword("password");
        return testUser;
    }


    public static User createTestUser(String username) {

        User testUser = new User();
        testUser.setUsername(username);
        testUser.setEmail(faker.internet().emailAddress());
        testUser.setPassword("password");
        return testUser;

    }
}
