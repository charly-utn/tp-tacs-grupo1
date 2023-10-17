package org.tptacs.infraestructure.repositories.interfaces;

import java.util.Optional;

import org.tptacs.domain.entities.UserOld;

public interface IUserRepository {
    Optional<UserOld> findByUsername(String userName);
    Optional<UserOld> findByEmail(String email);
    void save(UserOld user);
	Long countUserUnique();
	void update(UserOld user);
}
