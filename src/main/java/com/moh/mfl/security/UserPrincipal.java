package com.moh.mfl.security;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moh.mfl.model.ApiUsers;



public class UserPrincipal implements UserDetails {
	private final Long id;
	private final String name;
	private final String username;
	@JsonIgnore
	private final String email;
	@JsonIgnore
	private final String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long id, String username, String email, String password) {
		this.id = id;
		this.name = "";
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public static UserPrincipal create(ApiUsers user) {
		return new UserPrincipal(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserPrincipal that = (UserPrincipal) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}
}
