package com.devskiller.tasks.blog.service;


import com.devskiller.tasks.blog.model.Post;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.repository.PostRepository;

import java.time.LocalDateTime;

@Service
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public PostDto getPost(String id) {
		return postRepository.findById(id)
				.map(post -> new PostDto(post.title(), post.content(), post.creationDate()))
				.orElse(null);
	}

	public Post savePost(PostDto postDto){
			Post postModel =  new Post(null, postDto.title(), postDto.content(), LocalDateTime.now());
			return postRepository.save(postModel);
	}
}
