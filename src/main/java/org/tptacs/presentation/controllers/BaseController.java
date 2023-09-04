package org.tptacs.presentation.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.tptacs.domain.entities.User;

public abstract class BaseController {
    public User getUserFromJwt() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
