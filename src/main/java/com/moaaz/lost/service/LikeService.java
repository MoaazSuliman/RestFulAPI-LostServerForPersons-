package com.moaaz.lost.service;

import com.moaaz.lost.model.Like;
import com.moaaz.lost.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public Like getLikeByUserIdAndPostId(long userId, long postId) {
        return likeRepository.findLikeByUserIdAndPostId(userId, postId).orElse(null);
    }

    public void deleteLike(long likeId) {
        getLikeByIdOrElseThrowException(likeId);
        likeRepository.deleteById(likeId);
    }

    public void addLike(Like like) {
        likeRepository.save(like);
    }

    public Like getLikeByIdOrElseThrowException(long likeId) {
        return likeRepository.findById(likeId).orElseThrow(
                () -> new NoSuchElementException("There Are Not Like With Id = " + likeId)
        );
    }


}
