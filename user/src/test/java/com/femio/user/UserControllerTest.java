package com.femio.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userServiceTest;

    private UserEntity userEntityTestMockedResponse;

    @BeforeEach
    void setUp() {

        userEntityTestMockedResponse = UserEntity.builder()
                .id(88L)
                .username("aDefault")
                .password("bDefault")
                .fullName("cDefault")
                .address("dDefault")
                .userRank("eDefault")
                .imageLink("fDefault")
                .phoneNumber("gDefault")
                .build();
    }

    @Test
    void addUser() throws Exception {

        Mockito.lenient().when(userServiceTest.addUser(
                        Mockito.any(UserRequest.class)))
                .thenReturn(userEntityTestMockedResponse);

        mockMvc.perform(post("/api/v1/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"aDefault\",\n" +
                        "    \"password\":\"bDefault\",\n" +
                        "    \"fullName\":\"cDefault\",\n" +
                        "    \"address\":\"dDefault\",\n" +
                        "    \"phoneNumber\":\"gDefault\",\n" +
                        "    \"userRank\":\"eDefault\",\n" +
                        "    \"imageLink\":\"fDefault\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() {
    }
}