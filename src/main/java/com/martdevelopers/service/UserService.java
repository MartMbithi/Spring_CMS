package com.martdevelopers.service;

import com.martdevelopers.model.User;

public interface UserService {
  
 public User findUserByEmail(String email);
 
 public void saveUser(User user);
}
