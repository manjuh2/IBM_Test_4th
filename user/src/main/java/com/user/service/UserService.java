package com.user.service;

import java.util.List;

import com.user.payload.UserDto;

public interface UserService {

	UserDto createNewUser(UserDto userDto);

	List<UserDto> listAllUsers();

	void deleteUserById(Long id);

	UserDto updateUserById(Long id, UserDto userDto);

	UserDto updateUserCont(Long id);

}
