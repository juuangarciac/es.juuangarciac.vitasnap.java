package es.juuangarciac.vitasnap.java.post;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;

import com.github.javafaker.Faker;

import es.juuangarciac.vitasnap.post.domain.Post;
import es.juuangarciac.vitasnap.post.services.PostManagmentService;

public class ObjectMother {
    private static final Faker faker = new Faker();

    public LocalDate generarFechaAleatoria() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public Post createPostTest() {
        
        Post testPost = new Post();
        testPost.setId(UUID.randomUUID());
        testPost.setLocation(faker.address().fullAddress());
        testPost.setPublicationDate(generarFechaAleatoria());
        testPost.setPhotoCaption("Photo Caption");
        
        return testPost;
    }

}
