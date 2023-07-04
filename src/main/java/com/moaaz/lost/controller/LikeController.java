package com.moaaz.lost.controller;

import com.moaaz.lost.model.Like;
import com.moaaz.lost.model.Post;
import com.moaaz.lost.model.User;
import com.moaaz.lost.service.LikeService;
import com.moaaz.lost.service.PostService;
import com.moaaz.lost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    @PostMapping("/add/user/{userId}/post/{postId}")
    public ResponseEntity<?> addLike(@PathVariable long userId, @PathVariable long postId) {
        User user = userService.getUserByIdOrElseThrowException(userId);
        Post post = postService.getPostByIdOrElseTHrowException(postId);

        Like like = likeService.getLikeByUserIdAndPostId(userId, postId);
        if (like == null) {
            postService.addLikeForPost(post);
            Like newLike = new Like(0, userId, postId);
            likeService.addLike(newLike);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.ALREADY_REPORTED);

    }

    @PostMapping("/deleteLike/user/{userId}/post/{postId}")
    public ResponseEntity<?> deleteLike(@PathVariable long userId, @PathVariable long postId) {
        User user = userService.getUserByIdOrElseThrowException(userId);
        Post post = postService.getPostByIdOrElseTHrowException(postId);

        Like like = likeService.getLikeByUserIdAndPostId(userId, postId);
        if (like == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);

        }
        postService.deleteLikeForPost(post);
        likeService.deleteLike(like.getId());
        return new ResponseEntity<>(true, HttpStatus.OK);

    }

}
