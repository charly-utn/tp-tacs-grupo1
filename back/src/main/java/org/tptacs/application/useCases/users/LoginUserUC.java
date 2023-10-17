package org.tptacs.application.useCases.users;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.tptacs.application.security.JwtUtils;
import org.tptacs.domain.entities.User;
import org.tptacs.domain.enums.Rol;
import org.tptacs.presentation.requestModels.LoginRequest;
import org.tptacs.presentation.responseModels.LoginResponse;

@Service
public class LoginUserUC {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        Rol rol = Rol.valueOf(Objects.requireNonNull(userDetails.getAuthorities().stream().findFirst().orElse(null)).getAuthority());

        return new LoginResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                rol);
    }
}
