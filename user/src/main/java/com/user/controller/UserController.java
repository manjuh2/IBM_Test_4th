package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.payload.UserDto;
import com.user.service.UserService;

@RestController
@RequestMapping("/newUser")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// 1. Create users
	// http://localhost:8080/newUser
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto dto = userService.createNewUser(userDto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	// 2. Read user data
	// http://localhost:8080/newUser
	@GetMapping
	public List<UserDto> getAllUsers(){
		List <UserDto> dto = userService.listAllUsers();
		return dto;
	}
	
	// 3. Update user data
	// http://localhost:8080/newUser/2
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto){
		UserDto dto = userService.updateUserById(id, userDto);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	// 4. Delete a few attributes of user data
	// http://localhost:8080/newUser/2/Delete-Biology
	@PatchMapping("/{id}/Delete-Biology")
	public ResponseEntity<UserDto> updateUserCont(@PathVariable ("id") Long id){
		UserDto dto = userService.updateUserCont(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	// 5. Delete user records completely
	// http://localhost:8080/newUser/3
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable ("id") Long id){
		userService.deleteUserById(id);
		return new ResponseEntity<>("Student is Deleted", HttpStatus.OK);
	}
	
}
