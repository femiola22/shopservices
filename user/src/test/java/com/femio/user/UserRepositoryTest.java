package com.femio.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryTest;

    private UserEntity testUserEntity;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        testUserEntity = UserEntity.builder()
                .username("aDefault")
                .password("bDefault")
                .fullName("cDefault")
                .address("dDefault")
                .userRank("eDefault")
                .imageLink("fDefault")
                .phoneNumber("gDefault")
                .build();

        testEntityManager.persist(testUserEntity);
    }

    @Test
    void findUserById_thenReturnTheUser(){

        //when
        UserEntity actualUser = userRepositoryTest.findUserById(testUserEntity.getId());

        //then
        assertEquals(testUserEntity, actualUser);
    }

    @Test
    void ifDeleteUserByIdThenTheUserShouldNotBeFound() {
        //when
        userRepositoryTest.deleteUserById(testUserEntity.getId());

        //then
        assertNull(userRepositoryTest.findUserById(testUserEntity.getId()));

    }

    @Test
    void whenUsernameAndPasswordIsValidReturnUserObject() {
        Optional<UserEntity> actualUserEntity = userRepositoryTest
                .loginUser(testUserEntity.getUsername(), testUserEntity.getPassword());

        assertEquals(Optional.of(testUserEntity),actualUserEntity);
    }
}