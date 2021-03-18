package com.wesleysoares.scarffold.repository;

import com.wesleysoares.scarffold.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>
{

}
