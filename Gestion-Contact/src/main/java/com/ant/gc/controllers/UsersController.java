package com.ant.gc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ant.gc.entities.Users;
import com.ant.gc.models.MessageResponse;
import com.ant.gc.models.PasswordDTO;
import com.ant.gc.services.UsersService;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")

public class UsersController {
 @Autowired
 private UsersService usersService;
 @GetMapping
 	public List<Users> getAll(){
 		return usersService.findAll();
 	}
 @PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public MessageResponse add(@RequestBody Users user) {
		return usersService.save(user);
	}
 @PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping
	public MessageResponse edit(@RequestBody Users user) {
		return usersService.update(user);
	}
 @PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public MessageResponse delete(@PathVariable("id")Integer id) {
		return usersService.delete(id);
	}
 @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PatchMapping	
	public MessageResponse changePassword(@RequestBody PasswordDTO pwdDTO) {
		return usersService.changePassword(pwdDTO);
	}
 @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/{id}")	
	public Users findById(@PathVariable("id")Integer id) {
		return usersService.findById(id);
	}
}
