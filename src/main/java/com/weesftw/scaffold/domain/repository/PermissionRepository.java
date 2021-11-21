package com.weesftw.scaffold.domain.repository;

import com.weesftw.scaffold.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer>
{

}
