package com.femio.user;

import com.femio.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity addUser(UserRequest userRequest) {
        UserEntity userEntity = UserEntity.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .fullName(userRequest.getFullName())
                .address(userRequest.getAddress())
                .phoneNumber(userRequest.getPhoneNumber())
                .userRank(userRequest.getUserRank())
                .imageLink(userRequest.getImageLink())
                .build();

        return userRepository.save(userEntity);
    }

    public UserEntity updateUser(Long id, UserRequest userRequest){
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setFullName(userRequest.getFullName());
        userEntity.setAddress(userRequest.getAddress());
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setUserRank(userRequest.getUserRank());
        userEntity.setImageLink(userRequest.getImageLink());

        return userRepository.save(userEntity);
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findUserById(Long id){
        return userRepository.findUserById(id);
    }

    public void deleteUser(Long id){
        userRepository.deleteUserById(id);
    }

    public UserEntity loginUser(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return userRepository.loginUser(username, password)
                .orElseThrow(() -> new UserNotFoundException("User by username: "+username+" was not found"));
    }

}