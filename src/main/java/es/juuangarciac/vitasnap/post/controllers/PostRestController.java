package es.juuangarciac.vitasnap.post.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.juuangarciac.vitasnap.post.domain.Post;
import es.juuangarciac.vitasnap.post.services.PostManagmentService;

@RestController
@RequestMapping("/api/posts")
@ResponseStatus(HttpStatus.OK)
public class PostRestController {
    private final PostManagmentService postManagmentService;

    @Autowired
    public PostRestController(PostManagmentService postManagmentService) {
        this.postManagmentService = postManagmentService;
    }

    @GetMapping
    public List<Post> all() {
        return postManagmentService.loadPosts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void newPost(@RequestBody Post post) {
        postManagmentService.registerPost(post);
    }

    //Single item
    @GetMapping("/{id}")
    public Post one(@PathVariable String iUuid) {
        return postManagmentService.loadPostById(UUID.fromString(iUuid))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    @PutMapping("/{id}")
    public Post replacePost(@RequestBody Post post, @PathVariable String id) {
        return postManagmentService.updatePost(post, id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id) {
        //TO-DO
    }
}
