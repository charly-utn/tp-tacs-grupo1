package org.tptacs.presentation.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.tptacs.domain.entities.UserOld;

public class BaseController {
    public UserOld getUserFromJwt() {
        return (UserOld) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
