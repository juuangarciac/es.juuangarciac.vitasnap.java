package es.juuangarciac.vitasnap.java.post.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.juuangarciac.vitasnap.java.post.ObjectMother;
import es.juuangarciac.vitasnap.post.domain.Post;
import es.juuangarciac.vitasnap.post.repositories.PostRepository;
import es.juuangarciac.vitasnap.post.services.PostManagmentService;

@SpringBootTest
public class PostManagmentServiceTest {
    @Autowired
    private PostManagmentService postManagmentService;

    @MockBean
    private PostRepository postRepository;

    @Test
    public void shouldUpdatePhotoCaption() {
        //TO-DO: 
        //Given
        Post testPost = ObjectMother.createPostTest();
        postRepository.save(testPost);
        //When
        testPost.setLocation("Spain, Pto Santa Maria");
        postManagmentService.updatePost(testPost, testPost.getId().toString());
        given(postRepository.findById(testPost.getId())).willReturn(Optional.of(testPost));
        Optional<Post> post = postRepository.findById(testPost.getId());
        //Then
        assertThat(post.isPresent());
        try {
            assertEquals(post.get().getLocation(), "Spain, Pto Santa Maria");
        } catch(NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
