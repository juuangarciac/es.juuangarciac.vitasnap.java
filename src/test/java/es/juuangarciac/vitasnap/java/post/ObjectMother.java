package es.juuangarciac.vitasnap.java.post;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import es.juuangarciac.vitasnap.post.domain.Post;

public class ObjectMother {

    public static LocalDate generarFechaAleatoria() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static Post createPostTest() {
        
        Post testPost = new Post();
        testPost.setId(UUID.randomUUID());
        testPost.setLocation("Spain, Cadiz");
        testPost.setPublicationDate(generarFechaAleatoria());
        testPost.setPhotoCaption("Photo Caption");
        //TO-DO
        return testPost;
    }

}
