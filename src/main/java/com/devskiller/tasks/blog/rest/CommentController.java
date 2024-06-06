package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.exception.PostNotFoundException;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

	/*
	 *
	 - `POST` at `/posts/{id}/comments` which should:
	 - Save a new comment with current date and time for post with passed `{id}`
	 - Return `201 Created` if comment is created successfully for post with passed `{id}`
	 - Return `404 Not Found` if post with passed `{id}` does not exist


	 - `GET` at `/posts/{id}/comments` which should:
	 - Return all comments sorted by creation date in descending order for a post with passed `{id}`
	 - Return empty list if a post with passed `{id}` does not exists or when it does not contain any comments
	 */
    @PostMapping()
    public ResponseEntity<String> addComment(@PathVariable String postId, @RequestBody @Valid NewCommentDto newCommentDto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(postId, newCommentDto));
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("post with passed id does not exist");
        }
    }

    @GetMapping
    public ResponseEntity<Object> getComments(@PathVariable String postId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsForPost(postId));
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("post with passed id does not exist");
        }
    }

}
