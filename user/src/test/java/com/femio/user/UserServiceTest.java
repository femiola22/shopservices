package com.femio.user;

import com.femio.user.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userServiceUnderTest;

    @Mock
    private UserRepository userRepository;

    UserRequest defaultTestUserRequest;

    LoginRequest defaultTestLoginRequest;

    UserEntity mockedResponseOfSavedEntity;

    @BeforeEach
    void setUpMock() {
        userServiceUnderTest = new UserService(userRepository);

        defaultTestUserRequest = UserRequest.builder()
                .username("aDefault")
                .password("bDefault")
                .fullName("cDefault")
                .address("dDefault")
                .userRank("eDefault")
                .imageLink("fDefault")
                .phoneNumber("gDefault")
                .build();

        defaultTestLoginRequest = LoginRequest.builder()
                .username(defaultTestUserRequest.getUsername())
                .password(defaultTestUserRequest.getPassword())
                .build();

        mockedResponseOfSavedEntity = UserEntity.builder()
                .id(88L)
                .username(defaultTestUserRequest.getUsername())
                .password(defaultTestUserRequest.getPassword())
                .fullName(defaultTestUserRequest.getFullName())
                .address(defaultTestUserRequest.getAddress())
                .userRank(defaultTestUserRequest.getUserRank())
                .imageLink(defaultTestUserRequest.getImageLink())
                .phoneNumber(defaultTestUserRequest.getPhoneNumber())
                .build();

        Mockito.lenient().when(userRepository.save(
                Mockito.any(UserEntity.class)))
                .thenReturn(mockedResponseOfSavedEntity);

        Mockito.lenient().when(userRepository.loginUser(
                defaultTestLoginRequest.getUsername(),
                defaultTestLoginRequest.getPassword()))
                .thenReturn(Optional.of(mockedResponseOfSavedEntity));

        Mockito.lenient().when(userRepository.findById(
                        defaultTestUserRequest.getId()))
                .thenReturn(Optional.of(mockedResponseOfSavedEntity));

    }

    @Test
    void saveUserAndReturnEntityObject() {
        //given
        UserEntity actual = userServiceUnderTest.addUser(defaultTestUserRequest);

        //then
        assertNotNull(actual);

    }

    @Test
    @DisplayName("Get data based on valid username and password")
    public void whenUsernameAndPasswordIsValid_get_user() {

        UserEntity userEntity = userServiceUnderTest.loginUser(defaultTestLoginRequest);

        //then
        assertEquals(mockedResponseOfSavedEntity,userEntity);

    }

    @Test
    @DisplayName("Get data based on invalid username and password")
    public void whenUsernameAndPasswordIsNotValid_throwException() {
        LoginRequest shouldThrowException = new LoginRequest();
        shouldThrowException.setUsername(defaultTestLoginRequest.getUsername());
        shouldThrowException.setPassword("wrong password");
        assertThrows(UserNotFoundException.class, () ->
                userServiceUnderTest.loginUser(shouldThrowException));
    }

    @Test
    void updateUser() {
        UserEntity updateResponse = userServiceUnderTest.updateUser(
                defaultTestUserRequest.getId(),
                defaultTestUserRequest);

        assertEquals(mockedResponseOfSavedEntity,updateResponse);
    }
}