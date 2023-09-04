package org.tptacs.infraestructure.repositories.interfaces;

import org.tptacs.domain.entities.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> findByUsername(String userName);
    Optional<User> findByEmail(String email);
    void save(User user);
}
