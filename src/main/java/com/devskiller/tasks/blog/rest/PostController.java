package com.devskiller.tasks.blog.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.service.PostService;

@Controller
@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PostDto> getPost(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> savePost(@RequestBody @Valid PostDto postDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postService.savePost(postDto));
	}

}
