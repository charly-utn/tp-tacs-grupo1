package org.tptacs.application.useCases.users;

import org.tptacs.presentation.requestModels.CreateUserRequest;
import org.tptacs.presentation.responseModels.ResponseCreateUser;

public interface CreateUserUseCase {

    public ResponseCreateUser createUser(CreateUserRequest createUserRequest);
    
}
