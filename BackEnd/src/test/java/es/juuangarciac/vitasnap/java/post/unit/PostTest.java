package es.juuangarciac.vitasnap.java.post.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import es.juuangarciac.vitasnap.java.post.ObjectMother;
import es.juuangarciac.vitasnap.post.domain.Post;

public class PostTest {
    
    @Test
    public void shouldProvidephotoCaption() {
        //Given
        Post testPost = ObjectMother.createPostTest();
        //When
        String photoCaption = "Photo Caption";
        //Then
        assertEquals(testPost.getPhotoCaption(), photoCaption);
    }

    @Test
    public void shoulrProvideLocation() {
        //Given
        Post testPost = ObjectMother.createPostTest();
        //When
        String location = "Spain, Cadiz";
        //Then
        assertEquals(testPost.getLocation(), location);
    }
}
