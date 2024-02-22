package es.juuangarciac.vitasnap.post.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import es.juuangarciac.vitasnap.post.domain.Post;
import es.juuangarciac.vitasnap.post.repositories.PostRepository;

@Service
public class PostManagmentService {

    private final PostRepository postRepository;

    public PostManagmentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> loadPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> loadPostById(UUID iUuid) {
        return postRepository.findById(iUuid);
    }

    public boolean registerPost(Post post) {
        try {
            postRepository.save(post);
            return true;
        }catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public Optional<Post> updatePost(Post newPost, String id) {
        Optional<Post> post = postRepository.findById(UUID.fromString(id));
        if(post.isPresent()) {
            post.get().setPhotoCaption(newPost.getPhotoCaption());
            //TO-DO
            return post;
        } else {
            return Optional.empty();
        }
    }

    public void delete(UUID id){
        postRepository.deleteById(id);
    }

}
