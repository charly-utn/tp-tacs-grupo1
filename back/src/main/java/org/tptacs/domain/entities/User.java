package org.tptacs.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.tptacs.domain.enums.Rol;
import org.tptacs.infraestructure.config.CustomAuthorityDeserializer;

import java.util.Collection;
import java.util.List;

@Getter
public class User implements UserDetails {
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

    public User(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = List.of(new SimpleGrantedAuthority(Rol.BASIC.name()));
        this.rol = Rol.BASIC;
    }

    private User() {}

    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
