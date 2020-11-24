package com.ant.gc.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ant.gc.entities.Users;
import com.ant.gc.models.MessageResponse;
import com.ant.gc.models.PasswordDTO;

public interface UsersService extends UserDetailsService {
	public MessageResponse save(Users user);
	public MessageResponse update(Users user);
	public List<Users> findAll();
	public MessageResponse changePassword(PasswordDTO pwdDTO);
	public MessageResponse delete(Integer id);
	public Users findById(Integer id);
}
