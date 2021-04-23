package ru.aeon.test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aeon.test.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    /**gets user by name
     * @param name
     * @return user account
     */
    Optional<User> getByUsername(String name);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
