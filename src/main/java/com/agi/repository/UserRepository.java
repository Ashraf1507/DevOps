package com.agi.repository;

import com.agi.core.user.Role;
import com.agi.core.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findUsersByRolesId(Long id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
