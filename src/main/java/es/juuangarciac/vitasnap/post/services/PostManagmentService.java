package es.juuangarciac.vitasnap.post.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.juuangarciac.vitasnap.post.domain.Post;
import es.juuangarciac.vitasnap.post.repositories.PostRepository;

@Service
public class PostManagmentService {

    private final PostRepository postRepository;

    @Autowired
    public PostManagmentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

}
