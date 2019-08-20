package com.holeman79.shoppingbackend.user;

import com.holeman79.shoppingbackend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByNameOrEmail(String name, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByName(String name);

    Optional<User> findByUserId(String userId);

    Boolean existsByUserId(String userId);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    Optional<User> findBySocialId(String socialId);
}
