package es.juuangarciac.vitasnap.post.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import es.juuangarciac.vitasnap.post.domain.Post;
import es.juuangarciac.vitasnap.post.services.PostManagmentService;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {
    private final PostManagmentService service;

    public PostRestController(PostManagmentService service) {
        this.service = service;
    }

    @GetMapping
    public CollectionModel<EntityModel<Post>> all() {
        List<EntityModel<Post>> posts = service.loadPosts().stream()
            .map(
                post -> EntityModel.of(post,
                    linkTo(methodOn(PostRestController.class).one(post.getId().toString())).withSelfRel(),
                    linkTo(methodOn(PostRestController.class).all()).withRel("posts")))
            .collect(Collectors.toList());

        return CollectionModel.of(posts, linkTo(methodOn(PostRestController.class).all()).withSelfRel());
    }

    @PostMapping
    public void newPost(@RequestBody Post post) {
        service.registerPost(post);
    }

    //Single item
    @GetMapping("/{id}")
    public EntityModel<Post> one(@PathVariable String iUuid) {
        Post post = new Post();
        try{
            UUID postId = UUID.fromString(iUuid);

            post = service.loadPostById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

            return EntityModel.of(post,
                linkTo(methodOn(PostRestController.class).one(iUuid)).withSelfRel(),
                linkTo(methodOn(PostRestController.class).all()).withRel("posts")
            );
        }catch(IllegalArgumentException e){
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid UUID");
            return EntityModel.of(post);
        }
    }

    @PutMapping("/{id}")
    public Post replacePost(@RequestBody Post post, @PathVariable String id) {
        try{
            UUID.fromString(id);
            return service.updatePost(post, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
        }catch(IllegalArgumentException e){
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid UUID");
            return new Post();
        }
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id) {
        try{
            service.delete(UUID.fromString(id));
        }catch(IllegalArgumentException e){ 
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid UUID");
        }
    }
}
