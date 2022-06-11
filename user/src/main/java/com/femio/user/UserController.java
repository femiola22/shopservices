package com.femio.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public UserEntity addUser(@RequestBody UserRequest newUserRequest) {
        log.info("new user registration {}", newUserRequest );
        return userService.addUser(newUserRequest);
    }

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long id) {
        UserEntity userEntity = userService.findUserById(id);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id,
                                                       @RequestBody UserRequest userRequest) {
        UserEntity updatedUser = userService.updateUser(id, userRequest);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User with id: " + id + " deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<UserEntity> loginUser(@RequestBody LoginRequest loginRequest) {
        UserEntity userEntity = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }
}
