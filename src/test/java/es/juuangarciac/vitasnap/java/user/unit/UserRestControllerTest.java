package es.juuangarciac.vitasnap.java.user.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.juuangarciac.vitasnap.java.user.ObjectMother;
import es.juuangarciac.vitasnap.user.controllers.UserRestController;
import es.juuangarciac.vitasnap.user.domain.User;
import es.juuangarciac.vitasnap.user.services.UserManagementService;

@SpringBootTest
public class UserRestControllerTest {

    @Autowired
    private UserRestController controller;

    @MockBean
    private UserManagementService userManagementService;

    @Test
    public void shouldReturnListOfUsers() {

        // Given
        // a certain user
        User testUser = ObjectMother.createTestUser();

        // and the service is stubbed for the method loadActiveUsers
        given(userManagementService.loadActiveUsers()).willReturn(List.of(testUser));

        // When
        // the All method of the controller is invoked
        List<User> result = controller.all();

        // Then
        assertThat(result.contains(testUser));
    }
}
