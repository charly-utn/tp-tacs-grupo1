package org.tptacs.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tptacs.application.useCases.users.CreateUserUseCase;
import org.tptacs.application.useCases.users.LoginUserUC;
import org.tptacs.presentation.requestModels.CreateUserRequest;
import org.tptacs.presentation.requestModels.LoginRequest;
import org.tptacs.presentation.responseModels.LoginResponse;
import org.tptacs.presentation.responseModels.ResponseCreateUser;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Users")
@RequestMapping(value = "/v2/api/users", produces = "application/json", consumes = "application/json")
public class UserController {

    private final LoginUserUC loginUserUC;
    
    @Autowired
    private CreateUserUseCase createUserUseCase;

    public UserController(LoginUserUC loginUserUC) {
        this.loginUserUC = loginUserUC;
    }

    @PostMapping()
    public ResponseEntity<ResponseCreateUser> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(createUserUseCase.createUser(createUserRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(this.loginUserUC.login(loginRequest));
    }
}
