package com.weesftw.scaffold.api.controller;

import com.weesftw.scaffold.api.request.UserRequest;
import com.weesftw.scaffold.domain.model.User;
import com.weesftw.scaffold.domain.service.UserService;
import com.weesftw.scaffold.util.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
class UserControllerTest
{
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        BDDMockito.when(userService.findAll()).thenReturn(Arrays.asList(UserManager.createUserWithId()));
        BDDMockito.when(userService.findById(ArgumentMatchers.anyString())).thenReturn(UserManager.createUserWithId());
    }

    @Test
    @DisplayName("get returns list of users when successful")
    public void get_ReturnOk_WhenSuccessful() throws Exception
    {
        ResultActions resultActions = mockMvc.perform(get("/"));

        resultActions
                .andExpect(view().name("index"))
                .andExpect(model().attribute("users", hasSize(1)))
                .andExpect(model().attribute("user", isA(User.class)))
                .andExpect(model().attribute("createUser", isA(UserRequest.class)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getUser returns users when successful")
    public void getUser_ReturnUser_WhenSuccessful() throws Exception
    {
        ResultActions resultActions = mockMvc.perform(get("/{id}", "1"));

        resultActions
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Wesley"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("post returns users when successful")
    public void post_ReturnOk_WhenSuccessful() throws Exception
    {
        ResultActions resultActions = mockMvc.perform(post("/", UserManager.createUser()));

        resultActions
                .andExpect(view().name("redirect:/"))
                .andExpect(status().is3xxRedirection());
    }
    @Test
    @DisplayName("post returns users when successful")
    public void update_ReturnRedirect_WhenSuccessful() throws Exception
    {
        ResultActions resultActions = mockMvc.perform(post("/update/{id}", "1"));

        resultActions
                .andExpect(view().name("redirect:/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("post returns users when successful")
    public void delete_ReturnRedirect_WhenSuccessful() throws Exception
    {
        ResultActions resultActions = mockMvc.perform(post("/delete/{id}", "1"));

        resultActions
                .andExpect(view().name("redirect:/"))
                .andExpect(status().is3xxRedirection());
    }
}