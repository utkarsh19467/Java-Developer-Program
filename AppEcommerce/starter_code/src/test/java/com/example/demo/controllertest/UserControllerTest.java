package com.example.demo.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.EcommerceUser;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class UserControllerTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    /** The user controller. */
    @InjectMocks
    private UserController userController;

    /** The user repo. */
    @Mock
    private UserRepository userRepo;

    /** The cart repo. */
    @Mock
    private CartRepository cartRepo;

    /** The encoder. */
    @Mock
    private PasswordEncoder encoder;

    /** The mock mvc. */
    @Autowired
    private MockMvc mockMvc;

    /** The object mapper. */
    @Autowired
    private ObjectMapper objectMapper;

    /** The request. */
    @Autowired
    private MockHttpServletRequest request;

    /** The user. */
    private EcommerceUser user;

    /** The user request. */
    private CreateUserRequest userRequest;

    /**
     * Inits the.
     *
     * @throws Exception the exception
     */
    @BeforeEach
    public void init() throws Exception {

	userRequest = new CreateUserRequest();
	userRequest.setUsername("jwttestname2");
	userRequest.setPassword("testPassword");
	userRequest.setConfirmPassword("testPassword");

	MvcResult entityResult = mockMvc.perform(
		MockMvcRequestBuilders.post("/api/user/create").content(objectMapper.writeValueAsString(userRequest))
			.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andReturn();
	user = objectMapper.readValue(entityResult.getResponse().getContentAsString(), EcommerceUser.class);

	MvcResult result = mockMvc
		.perform(MockMvcRequestBuilders.post("/login").content(objectMapper.writeValueAsString(userRequest)))
		.andExpect(status().isOk()).andReturn();
	request.addParameter("Authorization", result.getResponse().getHeader("Authorization"));
    }

    /**
     * Test get user with username and id.
     *
     * @throws Exception the exception
     */
    @Test
    @DisplayName("Test method to test user fetching controllers using unique username and id")
    public void testGetUserWithUsernameAndId() throws Exception {

	when(userRepo.findByUsername(Mockito.anyString())).thenReturn(user);
	when(userRepo.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(user));

	mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{username}", "jwttestname2")
		.accept(MediaType.APPLICATION_JSON).header("Authorization", request.getParameter("Authorization")))
		.andExpect(status().isOk());

	mockMvc.perform(
		MockMvcRequestBuilders.get("/api/user/{username}", "jwttestname2").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());

	mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{username}", "DemoInput")
		.accept(MediaType.APPLICATION_JSON).header("Authorization", request.getParameter("Authorization")))
		.andExpect(status().isNotFound());

	mockMvc.perform(MockMvcRequestBuilders.get("/api/user/id/{id}", user.getId()).accept(MediaType.APPLICATION_JSON)
		.header("Authorization", request.getParameter("Authorization"))).andExpect(status().isOk());

	mockMvc.perform(
		MockMvcRequestBuilders.get("/api/user/id/{id}", user.getId()).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());

	mockMvc.perform(MockMvcRequestBuilders.get("/api/user/id/{id}", 100).accept(MediaType.APPLICATION_JSON)
		.header("Authorization", request.getParameter("Authorization"))).andExpect(status().isNotFound());
    }

}
