package com.wesleysoares.scaffold.api.controller;

import com.wesleysoares.scaffold.api.request.UserRequest;
import com.wesleysoares.scaffold.domain.model.User;
import com.wesleysoares.scaffold.domain.service.UserService;
import com.wesleysoares.scaffold.util.UserManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserControllerTest
{
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup()
    {
        BDDMockito.when(userService.findAll()).thenReturn(Arrays.asList(UserManager.createUserWithId()));
        BDDMockito.when(userService.save(ArgumentMatchers.any())).thenReturn(UserManager.createUserWithId());
        BDDMockito.when(userService.findById(ArgumentMatchers.anyString())).thenReturn(UserManager.createUserWithId());
        BDDMockito.when(userService.findByName(ArgumentMatchers.anyString())).thenReturn(UserManager.createUserWithId());
        BDDMockito.doNothing().when(userService).deleteById(ArgumentMatchers.anyString());
    }

    @Test
    @DisplayName("Find all users and return when successful")
    public void findAll_ReturnListUser_WhenSuccessful()
    {
        List<User> listReturned = userController.getAll().getBody();
        User userToGetName = UserManager.createUser();

        Assertions.assertThat(listReturned).isNotNull();
        Assertions.assertThat(listReturned.size()).isEqualTo(1);
        Assertions.assertThat(listReturned.get(0).getName()).isEqualTo(userToGetName.getName());
    }

    @Test
    @DisplayName("Create user when successful")
    public void create_User_WhenSuccessful()
    {
        String idExpected = UserManager.createUserWithId().getId();
        UserRequest userToBeSaved = modelMapper.map(UserManager.createUser(), UserRequest.class);
        User userSaved = userController.post(userToBeSaved).getBody();

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isEqualTo(idExpected);
    }
}