package com.moaaz.lost.repository;

import com.moaaz.lost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmailAndPassword(String email, String password);

    public Optional<User> findByEmail(String email);
}
