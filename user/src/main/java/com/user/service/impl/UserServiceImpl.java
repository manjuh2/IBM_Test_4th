package com.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.exceptions.ResourceNotFoundException;
import com.user.payload.UserDto;
import com.user.repository.UserRepository;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDto createNewUser(UserDto userDto) {
		User user = new User();
		user.setBiology(userDto.getBiology());
		user.setChemistry(userDto.getChemistry());
		user.setMaths(userDto.getMaths());
		user.setPhysics(userDto.getPhysics());
		user.setStudentName(userDto.getStudentName());
		
		User save = userRepository.save(user);
		
		UserDto dto = new UserDto();
		dto.setId(save.getId());
		dto.setBiology(save.getBiology());
		dto.setChemistry(save.getChemistry());
		dto.setMaths(save.getMaths());
		dto.setPhysics(save.getPhysics());
		dto.setStudentName(save.getStudentName());
		
		return dto;
	}

	@Override
	public List <UserDto> listAllUsers() {
		List<User> user = userRepository.findAll();
		List<UserDto> dto = user.stream().map(x-> mapToDto(x)).collect(Collectors.toList());
		return dto;
	}
	
	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDto updateUserById(Long id, UserDto userDto) {
		User user = mapToEntity(userDto);
		user.setId(id);
		User updatedUser = userRepository.save(user);
		
		UserDto dto = mapToDto(updatedUser);
		return dto;
	}


	UserDto mapToDto (User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setBiology(user.getBiology());
		dto.setChemistry(user.getChemistry());
		dto.setMaths(user.getMaths());
		dto.setPhysics(user.getPhysics());
		dto.setStudentName(user.getStudentName());
		return dto;
	}
	
	User mapToEntity (UserDto userDto) {
		User user = new User();
		user.setBiology(userDto.getBiology());
		user.setChemistry(userDto.getChemistry());
		user.setMaths(userDto.getMaths());
		user.setPhysics(userDto.getPhysics());
		user.setStudentName(userDto.getStudentName());
		return user;
	}

	@Override
	public UserDto updateUserCont(Long id) {
		
		User user = userRepository.findById(id).orElseThrow(
						()-> new ResourceNotFoundException("User is Not found for the id : "+id)
						);
		
		user.setBiology(0);
		User updatedUser = userRepository.save(user);
		UserDto dto = mapToDto(updatedUser);
		return dto;
	}

}
