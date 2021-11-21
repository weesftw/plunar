package com.weesftw.scaffold.domain.repository;

import com.weesftw.scaffold.domain.model.Account;
import com.weesftw.scaffold.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>
{
    Account findByUsername(String username);

    @Query("SELECT g FROM Group AS g INNER JOIN Account AS a ON a.group.id = g.id WHERE a.uuid = :uuid")
    Group getAccountGroup(UUID uuid);
}
