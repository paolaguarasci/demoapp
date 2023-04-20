package it.pingflood.florencedemo.service.impl;

import it.pingflood.florencedemo.data.User;
import it.pingflood.florencedemo.data.dto.UserCreate;
import it.pingflood.florencedemo.data.dto.UserResponse;
import it.pingflood.florencedemo.data.dto.UserUpdate;
import it.pingflood.florencedemo.repository.UserRepository;
import it.pingflood.florencedemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {
  
  private final UserRepository userRepository;
  private ModelMapper modelMapper;
  
  public UserServiceImpl(UserRepository userRepository) {
    this.modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
      .setFieldMatchingEnabled(true)
      .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    this.userRepository = userRepository;
  }
  
  @Override
  public List<UserResponse> getAllUsers() {
    return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
  }
  
  @Override
  public UserResponse getUser(Long id) {
    return modelMapper.map(userRepository.findById(id).orElseThrow(), UserResponse.class);
  }
  
  @Override
  public UserResponse saveUser(UserCreate userCreate) {
    // TODO Un minimo di validazione...
    return modelMapper.map(userRepository.save(modelMapper.map(userCreate, User.class)), UserResponse.class);
  }
  
  @Override
  public UserResponse updateUser(Long id, UserUpdate userUpdate) {
    // TODO Un minimo di validazione...
    if (!Objects.equals(userUpdate.id, id)) {
      throw new RuntimeException("Update error");
    }
    User userDB = userRepository.findById(id).orElseThrow();
    userDB.update(modelMapper.map(userUpdate, User.class));
    return modelMapper.map(userRepository.save(userDB), UserResponse.class);
  }
  
  @Override
  public List<UserResponse> saveFromCSV() {
    
    return null;
  }
  
  
  @Override
  public void deleteUser(Long id) {
    userRepository.delete(userRepository.findById(id).orElseThrow());
  }
}
