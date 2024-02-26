package es.juuangarciac.vitasnap.post.repositories;

import es.juuangarciac.vitasnap.album.domain.Album;
import es.juuangarciac.vitasnap.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    Optional<Post> findById(UUID iUuid);

    List<Album> findByAlbumId(UUID iUuid);
    List<Album> findByPublicationDate(LocalDate date);
    List<Album> findByLocation(String location);
}
