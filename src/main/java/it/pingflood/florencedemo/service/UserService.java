package it.pingflood.florencedemo.service;

import it.pingflood.florencedemo.data.dto.UserCreate;
import it.pingflood.florencedemo.data.dto.UserResponse;
import it.pingflood.florencedemo.data.dto.UserUpdate;

import java.util.List;


public interface UserService {
  List<UserResponse> getAllUsers();
  
  UserResponse getUser(Long id);
  
  UserResponse saveUser(UserCreate userCreate);
  
  UserResponse updateUser(Long id, UserUpdate userUpdate);
  
  void deleteUser(Long id);
}
