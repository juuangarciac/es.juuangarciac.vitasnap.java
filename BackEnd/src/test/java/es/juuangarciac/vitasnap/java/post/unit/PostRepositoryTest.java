package es.juuangarciac.vitasnap.java.post.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.juuangarciac.vitasnap.java.post.ObjectMother;
import es.juuangarciac.vitasnap.post.domain.Post;
import es.juuangarciac.vitasnap.post.repositories.PostRepository;

@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void shouldNotFindANotExistingPost() {
        //Given
        UUID uuid = UUID.randomUUID();
        //When
        Optional<Post> testPost = postRepository.findById(uuid);
        //Then
        assertThat(testPost.isPresent()).isFalse();
    }

    public void shouldFindAnExistingPost() {
        //Given
        Post testPost = ObjectMother.createPostTest();
        postRepository.save(testPost);
        //When
        Optional<Post> post = postRepository.findById(testPost.getId());
        //Then
        assertThat(post.isPresent());
    }
}
