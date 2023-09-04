package org.tptacs.infraestructure.repositories;

import org.springframework.stereotype.Repository;
import org.tptacs.domain.entities.User;
import org.tptacs.infraestructure.repositories.interfaces.IUserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositority implements IUserRepository {
    private final Map<String, User> repository = new HashMap<>();
    @Override
    public Optional<User> findByUsername(String userName) {
        return this.repository.values()
                .stream().filter(v -> v.getUsername().equals(userName))
                .findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.repository.values()
                .stream().filter(v -> v.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public void save(User user) {
        this.repository.put(user.getId(), user);
    }
}
