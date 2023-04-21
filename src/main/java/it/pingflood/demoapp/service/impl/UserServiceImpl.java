package it.pingflood.demoapp.service.impl;

import com.opencsv.CSVReader;
import it.pingflood.demoapp.data.User;
import it.pingflood.demoapp.data.dto.UserCreate;
import it.pingflood.demoapp.data.dto.UserResponse;
import it.pingflood.demoapp.data.dto.UserUpdate;
import it.pingflood.demoapp.data.vo.Address;
import it.pingflood.demoapp.data.vo.Email;
import it.pingflood.demoapp.data.vo.FirstName;
import it.pingflood.demoapp.data.vo.LastName;
import it.pingflood.demoapp.repository.UserRepository;
import it.pingflood.demoapp.repository.specs.UserSpecs;
import it.pingflood.demoapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
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
  public List<UserResponse> saveFromCSV(MultipartFile file) throws Exception {
    CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(file.getInputStream())));
    List<String[]> rows = reader.readAll();
    List<UserCreate> userCreates = new ArrayList<>();
    rows.forEach(row -> userCreates.add(modelMapper.map(row2User(row), UserCreate.class)));
    return saveUsersList(userCreates);
  }
  
  @Override
  public List<UserResponse> saveUsersList(List<UserCreate> userCreateList) {
    return userCreateList.stream().map(userCreate ->
      modelMapper.map(userRepository.save(modelMapper.map(userCreate, User.class)), UserResponse.class)
    ).collect(Collectors.toList());
  }
  
  @Override
  public List<UserResponse> getUsersByFirstNameAndLastName(String firstNameString, String lastNameString) {
    
    List<User> result;
    if ((firstNameString != null && !firstNameString.trim().isEmpty()) && (lastNameString != null && !lastNameString.trim().isEmpty())) {
      FirstName firstName = FirstName.builder().firstName(firstNameString).build();
      LastName lastName = LastName.builder().lastName(lastNameString).build();
      Specification<User> specFL = UserSpecs.firstNameOrlastNameContainsIgnoreCase(firstName, lastName);
      result = userRepository.findAll(specFL);
    } else if ((firstNameString != null && !firstNameString.trim().isEmpty())) {
      FirstName firstName = FirstName.builder().firstName(firstNameString).build();
      Specification<User> specF = UserSpecs.firstNameContainsIgnoreCase(firstName);
      result = userRepository.findAll(specF);
    } else if (lastNameString != null && !lastNameString.trim().isEmpty()) {
      LastName lastName = LastName.builder().lastName(lastNameString).build();
      Specification<User> specL = UserSpecs.lastNameContainsIgnoreCase(lastName);
      result = userRepository.findAll(specL);
    } else {
      result = userRepository.findAll();
    }
    
    return result.stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
  }
  
  @Override
  public void deleteUser(Long id) {
    userRepository.delete(userRepository.findById(id).orElseThrow());
  }
  
  private User row2User(String[] row) {
    return User.builder()
      .firstName(FirstName.builder().firstName(row[0]).build())
      .lastName(LastName.builder().lastName(row[1]).build())
      .email(Email.builder().email(row[2]).build())
      .address(Address.builder()
        .line1(row[3])
        .line2(row[4])
        .postalCode(row[5])
        .city(row[6])
        .state(row[7])
        .build())
      .build();
  }
  
}
