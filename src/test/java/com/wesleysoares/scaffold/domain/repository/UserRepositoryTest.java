package com.wesleysoares.scaffold.domain.repository;

import com.wesleysoares.scaffold.ScaffoldApplication;
import com.wesleysoares.scaffold.domain.model.User;
import com.wesleysoares.scaffold.util.UserManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest
{
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Creates user when successful")
    public void save_PersistUserInDatabase_WhenSuccessful()
    {
        User userToBeSaved = UserManager.createUser();
        User userSaved = userRepository.save(userToBeSaved);

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isNotNull();
        assertEquals(userToBeSaved.getName(), userSaved.getName());
    }

    @Test
    @DisplayName("Finds user by name when successful")
    public void find_UserByName_WhenSuccessful()
    {
        User userToBeSaved = userRepository.save(UserManager.createUser());
        User userFindByName = userRepository.findByName("Wesley").get();

        Assertions.assertThat(userFindByName).isNotNull();
        Assertions.assertThat(userFindByName.getName()).isEqualTo("Wesley");
    }

    @Test
    @DisplayName("Put user by id when successful")
    public void put_UserById_WhenSuccessful()
    {
        User userToBeSaved = userRepository.save(UserManager.createUser());
        User userToBeChanged = userRepository.findByName("Wesley").get();
        userToBeSaved.setId(userToBeChanged.getId());
        userToBeSaved.setName("Leticia");

        User userSaved = userRepository.save(userToBeSaved);

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getName()).isEqualTo("Leticia");
    }

    @Test
    @DisplayName("Finds all users when successful")
    public void findAll_Users_WhenSuccessful()
    {
        User userToBeSaved = userRepository.save(UserManager.createUser());
        List<User> getAllUsers = userRepository.findAll();

        assertEquals(getAllUsers.size(), 1);
    }

    @Test
    @DisplayName("Delete user by id when successful")
    public void delete_UserById_WhenSuccessful()
    {
        User userToBeDeleted = userRepository.save(UserManager.createUser());
        userRepository.delete(userToBeDeleted);
        Optional<User> userToBeSearch = userRepository.findByName(userToBeDeleted.getName());

        Assertions.assertThat(userToBeSearch.isEmpty()).isTrue();
    }
}