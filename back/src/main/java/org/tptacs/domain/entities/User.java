package org.tptacs.domain.entities;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.tptacs.domain.enums.Rol;

import lombok.Getter;

@Getter
@Document(collection = "users")
public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private Rol rol;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;
    private List<String> orderShared = new LinkedList<String>();
}
