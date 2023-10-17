package org.tptacs.infraestructure.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.tptacs.domain.entities.UserOld;
import org.tptacs.infraestructure.repositories.interfaces.IUserRepository;

@Repository
public class UserRepositorityOld extends FileRepository<UserOld> implements IUserRepository {
    public UserRepositorityOld() {
        super(UserRepositorityOld.class.getSimpleName(), UserOld.class);
    }

    @Override
    public Optional<UserOld> findByUsername(String userName) {
        return values()
                .stream().filter(v -> v.getUsername().equals(userName))
                .findFirst();
    }

    @Override
    public Optional<UserOld> findByEmail(String email) {
        return values()
                .stream().filter(v -> v.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public void save(UserOld user) {
        super.put(user.getId(), user);
    }

	@Override
	public Long countUserUnique() {
		return (long) values().size();
	}

	public void update(UserOld user) {
		super.replace(user.getId(), user);
	}

}
