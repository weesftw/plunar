package com.wesleysoares.scaffold.domain.service;

import com.wesleysoares.scaffold.domain.exception.UserNotFoundException;
import com.wesleysoares.scaffold.domain.model.User;
import com.wesleysoares.scaffold.domain.repository.UserRepository;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class UserServiceTest
{
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup()
    {
        BDDMockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(UserManager.createUserWithId());
        BDDMockito.when(userRepository.save(UserManager.createUserWithId())).thenReturn(UserManager.createUserUpdated());
        BDDMockito.when(userRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(UserManager.createUserWithId()));
        BDDMockito.when(userRepository.findAll()).thenReturn(Arrays.asList(UserManager.createUserWithId()));
        BDDMockito.doNothing().when(userRepository).delete(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Find all users and return when successful")
    public void getAll_ReturnListUser_WhenSuccessful()
    {
        List<User> listAll = userService.findAll();

        Assertions.assertThat(listAll)
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(listAll.get(0).getName()).isEqualTo("Wesley");
    }

    @Test
    @DisplayName("Create user when successful")
    public void post_User_WhenSuccessful()
    {
        User savedUser = userService.save(null);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isEqualTo("1");
    }

    @Test
    @DisplayName("Find user by id when successful")
    public void get_FindUserById_WhenSuccessful()
    {
        User userToBeSearchById = userService.findById("1");

        Assertions.assertThat(userToBeSearchById).isNotNull();
        Assertions.assertThat(userToBeSearchById.getId()).isEqualTo("1");
        Assertions.assertThat(userToBeSearchById.getName()).isEqualTo("Wesley");
    }

    @Test
    @DisplayName("Put user name when successful")
    public void put_UserName_WhenSuccessful()
    {
        User updatedUser = userService.save(UserManager.createUserWithId());

        Assertions.assertThat(updatedUser).isNotNull();
        Assertions.assertThat(updatedUser.getName()).isEqualTo("Leticia");
    }

    @Test
    @DisplayName("Delete user by id when successful")
    public void delete_UserByID_WhenSuccessful()
    {
        Assertions.assertThatCode(
                () -> userService.deleteById(UserManager.createUserWithId().getId())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("findById Throws UserNotFoundException when user by id not found")
    public void findById_ThrowsUserNotFoundException_WhenUserByIdNotFound()
    {
        BDDMockito.when(userService.findById(ArgumentMatchers.anyString()))
                .thenThrow(new UserNotFoundException("User not found."));

        Assertions.assertThatExceptionOfType(UserNotFoundException.class)
                .isThrownBy(() -> userService.findById(UserManager.createUserWithId().getId()));
    }
}