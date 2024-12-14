package com.sportseventapplication.service;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sportseventapplication.entity.*;

import com.sportseventapplication.DTO.CredentialDto;
import com.sportseventapplication.DTO.UserDTO;
import com.sportseventapplication.DTO.UserMapper;
import com.sportseventapplication.auth.service.JWTService;
import com.sportseventapplication.entity.User;
import com.sportseventapplication.exception.UsernameAlreadyExistsException;
import com.sportseventapplication.repository.PlayerRepository;
import com.sportseventapplication.repository.UserRepository;
import com.sportseventapplication.response.ResponseApi;

@Service
public class UserService implements IUserService {
	
	
	@Autowired
	private JWTService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PlayerRepository playRepository;
	
	@Autowired
	private AuthenticationManager authManager;

	@Override
	public UserDTO createUser(User user) {
		
		
	 return	Optional.of(user).filter(u-> !userRepository.existsByUsername(user.getUsername())).map(req->{
			
			User newUser =userRepository.save(user);
			Player player = new Player();
			
			if(newUser.getRole()==Role.PLAYER)
			{		
				player.setUser(newUser);
				player.setPlayerName(newUser.getUsername() );
				//user.setPassword(passwordEncoder.encode(user.getPassword()));	
				playRepository.save(player);
				
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			return userMapper.toUserDTO(userRepository.save(newUser));
			
		}).orElseThrow(()->{throw new UsernameAlreadyExistsException("Username "+user.getUsername()+" already exists");});
		
		
		
	}

	@Override 
	public List<User> getAllUser() {

		return userRepository.findAll();
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public UserDTO updateUser(User user, Long id) {
		user.setId(id);
		User updateUser = userRepository.findById(id).get();
		return createUser(updateUser);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);

	}
	public UserDTO convertToDTO(User user) {
		return new UserDTO(user.getId(),user.getUsername(),user.getEmail(),user.getRole());
	}

	@Override
	public Optional<User> findByusername(String username) {
		
		return userRepository.findByusername(username);
	}

	public UserDTO verify(CredentialDto credential) {
		// TODO Auto-generated method stub
		
		Authentication authentication = authManager.authenticate
				(new UsernamePasswordAuthenticationToken(
				credential.username(), credential.password(),Collections.emptyList()));
		UserDTO userDto = new UserDTO();
		
		if(authentication.isAuthenticated()) {
			User user = userRepository.findByusername(credential.username()).get();
			userDto = userMapper.toUserDTO(user);
			userDto.setToken(jwtService.createToken(user));
		}
		else {
			System.err.println("Access Denied");
		}
		return userDto;
	}
	
	
	public boolean usernameExists(String username)
	{
		return userRepository.existsByUsername(username);
	}

}
