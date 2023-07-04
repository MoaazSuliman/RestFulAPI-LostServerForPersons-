package com.moaaz.lost.repository;

import com.moaaz.lost.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    public Optional<Like> findLikeByUserIdAndPostId(long userId, long postId);
}
