package com.ant.gc.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ant.gc.repositories.UsersRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Users extends Contact implements UserDetails{
private String username;
private String password;
private String role;
@Override
@JsonIgnore
public Collection<? extends GrantedAuthority> getAuthorities() {
	List<GrantedAuthority> authorities = new ArrayList<>();
	
	authorities.add(new SimpleGrantedAuthority("ROLE_"+ role));
	return authorities;
}
@Override
@JsonIgnore
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
}
@Override
@JsonIgnore
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}
@Override
@JsonIgnore
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@JsonIgnore
@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
}
}
