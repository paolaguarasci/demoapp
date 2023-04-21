package it.pingflood.demoapp.service;

import it.pingflood.demoapp.data.dto.UserCreate;
import it.pingflood.demoapp.data.dto.UserResponse;
import it.pingflood.demoapp.data.dto.UserUpdate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {
  List<UserResponse> getAllUsers();
  
  UserResponse getUser(Long id);
  
  UserResponse saveUser(UserCreate userCreate);
  
  UserResponse updateUser(Long id, UserUpdate userUpdate);
  
  List<UserResponse> saveFromCSV(MultipartFile file) throws Exception;
  
  List<UserResponse> saveUsersList(List<UserCreate> userCreateList);
  
  void deleteUser(Long id);
  
  
  List<UserResponse> getUsersByFirstNameAndLastName(String firstName, String lastName);
}
