package es.juuangarciac.vitasnap.album.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.juuangarciac.vitasnap.album.domain.Album;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface AlbumRepository extends JpaRepository<Album, UUID> {
    Optional<Album> findById(UUID iUuid);
    List<Album> findByuserOwnerId(UUID iUuid);
    //TO-DO
}
