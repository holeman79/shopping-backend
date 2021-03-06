package com.holeman79.shoppingbackend.user.repository;

import com.holeman79.shoppingbackend.user.domain.Role;
import com.holeman79.shoppingbackend.user.domain.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType roleType);
}
