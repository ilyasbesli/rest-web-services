package com.ibesli.restwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jpa")
public class UserJpaResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
        return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
        {
			throw new UserNotFoundException("id:" + id + " user not found");
		}

        Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
	}

    @GetMapping("/users/{userId}/posts")
    public List<Post> retrieveUserPosts(@PathVariable int userId)
    {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent())
        {
            throw new UserNotFoundException("id:" + userId + " user not found");
        }
        return user.get().getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post)
    {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
        {
            throw new UserNotFoundException("id:" + id + " user not found");
        }

        User user = userOptional.get();

        post.setUser(user);
        postRepository.save(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
