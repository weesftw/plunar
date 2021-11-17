package com.weesftw.scaffold.domain.repository;

import com.weesftw.scaffold.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{

}
