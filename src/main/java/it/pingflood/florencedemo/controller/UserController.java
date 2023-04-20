package it.pingflood.florencedemo.controller;

import it.pingflood.florencedemo.data.dto.UserCreate;
import it.pingflood.florencedemo.data.dto.UserResponse;
import it.pingflood.florencedemo.data.dto.UserUpdate;
import it.pingflood.florencedemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
  
  private final UserService userService;
  
  public UserController(UserService userService) {
    this.userService = userService;
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getOneUserByID(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUser(id));
  }
  
  
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
