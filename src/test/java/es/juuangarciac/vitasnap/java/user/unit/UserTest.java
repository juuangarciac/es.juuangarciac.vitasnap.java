package es.juuangarciac.vitasnap.java.user.unit;

import org.junit.jupiter.api.Test;

import es.juuangarciac.vitasnap.java.user.ObjectMother;
import es.juuangarciac.vitasnap.user.domain.User;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    
    @Test
    public void shouldProvideUsername(){
        // Given
        // a certain user (not stored on the database)
        User testUser = ObjectMother.createTestUser("john");

        // When
        // I invoke getUsername method
        String username = testUser.getUsername();

        // Then the result is equals to the provided username
        assertThat(username.equals("john")).isTrue();

    }
}
