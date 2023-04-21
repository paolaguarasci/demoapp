package it.pingflood.demoapp.controller;

import it.pingflood.demoapp.data.dto.UserCreate;
import it.pingflood.demoapp.data.dto.UserResponse;
import it.pingflood.demoapp.data.dto.UserUpdate;
import it.pingflood.demoapp.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
  
  private final UserService userService;
  
  public UserController(UserService userService) {
    this.userService = userService;
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getOneUserByID(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUser(id));
  }
  
  @GetMapping("/search")
  public ResponseEntity<List<UserResponse>> getOneUserByID(@RequestParam(name = "firstname", required = false) String firstName, @RequestParam(name = "lastname", required = false) String lastName) {
    return ResponseEntity.ok(userService.getUsersByFirstNameAndLastName(firstName, lastName));
  }
  
  
  @SneakyThrows
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserResponse> createUser(@RequestBody UserCreate userCreate) {
    return ResponseEntity.ok(userService.saveUser(userCreate));
  }
  
  @PostMapping(value = "/csv", headers = ("content-type=multipart/form-data"))
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<List<UserResponse>> createUserFromCSV(@RequestParam("file") MultipartFile file) throws Exception {
    return ResponseEntity.ok(userService.saveFromCSV(file));
  }
  
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserUpdate userUpdate) {
    return ResponseEntity.ok(userService.updateUser(id, userUpdate));
  }
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
