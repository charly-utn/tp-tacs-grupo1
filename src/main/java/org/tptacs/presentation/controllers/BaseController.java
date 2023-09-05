package org.tptacs.presentation.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.tptacs.domain.entities.User;

@Component
public class BaseController {
    public User getUserFromJwt() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
