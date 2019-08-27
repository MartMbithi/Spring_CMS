package com.martdevelopers.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.martdevelopers.model.Role;
import com.martdevelopers.model.User;
import com.martdevelopers.repository.RoleRespository;
import com.martdevelopers.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;


@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private RoleRespository roleRespository;
	 
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;

	 @Override
	 public User findUserByEmail(String email) {
	  return userRepository.findByEmail(email);
	 }

	 @Override
	 public void saveUser(User user) {
	  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	  user.setActive(1);
	  Role userRole = roleRespository.findByRole("ADMIN");
	  user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	  userRepository.save(user);
	 }

}
