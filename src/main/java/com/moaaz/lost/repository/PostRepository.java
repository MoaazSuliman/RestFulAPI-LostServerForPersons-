package com.moaaz.lost.repository;

import com.moaaz.lost.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {



    public List<Post> findAllByOrderByLikesDesc();

    public List<Post> findByNameContainingOrDetailsContaining(String name ,  String details);
}
