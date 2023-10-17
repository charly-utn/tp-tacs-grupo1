package org.tptacs.application.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tptacs.application.useCases.users.CreateUserUseCase;
import org.tptacs.domain.entities.User;
import org.tptacs.domain.exceptions.RegistrationException;
import org.tptacs.infraestructure.repositories.UserRepository;
import org.tptacs.presentation.requestModels.CreateUserRequest;
import org.tptacs.presentation.responseModels.ResponseCreateUser;

@Service
public class CreateUserService implements CreateUserUseCase {
	
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private UserRepository userRepository;

	@Override
    public ResponseCreateUser createUser(CreateUserRequest createUserRequest) {
        var userNameOptional = userRepository.findByUsername(createUserRequest.getUserName());
        var emailOptional = this.userRepository.findByEmail(createUserRequest.getEmail());

        if (userNameOptional.isPresent()) throw new RegistrationException("user already exists");
        if (emailOptional.isPresent()) throw new RegistrationException("email already exists");

        User user = new User(UUID.randomUUID().toString(), createUserRequest.getUserName(),
                createUserRequest.getEmail(),
                encoder.encode(createUserRequest.getPassword()));

        userRepository.save(user);
        return new ResponseCreateUser(user.getId());
    }

}
