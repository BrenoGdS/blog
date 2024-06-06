package com.devskiller.tasks.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.devskiller.tasks.blog.exception.PostNotFoundException;
import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.repository.CommentRepository;
import com.devskiller.tasks.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;

@Service
public class CommentService {

	CommentRepository commentRepository;
	PostRepository postRepository;

	@Autowired
	public CommentService(CommentRepository commentRepository, PostRepository postRepository){
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(String postId) {
		if(!postRepository.existsById(postId)){
			throw new PostNotFoundException();
		}
		return commentRepository.findByPostIdOrderByCreationDateDesc(postId).stream()
				.map(comment -> new CommentDto(comment.id(), comment.comment(),comment.author(), comment.creationDate()))
				.collect(Collectors.toList());
	}

	/**
	 * Creates a new comment
	 *
	 * @param postId id of the post
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if postId is null or there is no blog post for passed postId
	 */
	public String addComment(String postId, NewCommentDto newCommentDto) {
		if(!postRepository.existsById(postId)){
			throw new PostNotFoundException();
		}
		Comment comment = new Comment(null, postId, newCommentDto.content(), newCommentDto.author(), LocalDateTime.now()) ;
		Comment savedComment = commentRepository.save(comment);
		return savedComment.id();
	}
}
