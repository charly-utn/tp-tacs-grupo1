package org.tptacs.domain.entities;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.tptacs.domain.enums.Rol;

import lombok.Getter;

@Getter
@Document(collection = "users")
public class User {
	@Id
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
    
	public User(String id, String userName, String email, String password) {
        this.id = id;
        this.username = userName;
        this.email = email;
        this.password = password;
        this.authorities = List.of(new SimpleGrantedAuthority(Rol.BASIC.name()));
        this.rol = Rol.BASIC;
    }

	public void addOrderShared(String id) {
		if(orderShared != null) orderShared.add(id);
		else orderShared = List.of(id);
		
	}
}
