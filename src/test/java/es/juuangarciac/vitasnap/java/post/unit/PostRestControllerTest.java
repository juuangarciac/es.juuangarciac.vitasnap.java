package es.juuangarciac.vitasnap.java.post.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.juuangarciac.vitasnap.java.post.ObjectMother;
import es.juuangarciac.vitasnap.post.controllers.PostRestController;
import es.juuangarciac.vitasnap.post.domain.Post;
import es.juuangarciac.vitasnap.post.services.PostManagmentService;

@SpringBootTest
public class PostRestControllerTest {
    @Autowired
    private PostRestController postRestController;
    @MockBean
    private PostManagmentService postManagmentService;

    @Test
    public void shouldReturnListOfPost() {
        //Given
        //Post testPost = ObjectMother.createPostTest();
        // and the service is stubbed for the method loadActiveUsers
        //given(postManagmentService.loadPosts()).willReturn(List.of(testPost));
        //When
        //List<Post> allPosts = postRestController.all();
        //Then
        //assertThat(allPosts.contains(testPost));

    }
}
