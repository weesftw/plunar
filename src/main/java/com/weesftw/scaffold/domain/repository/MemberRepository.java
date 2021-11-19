package com.weesftw.scaffold.domain.repository;

import com.weesftw.scaffold.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>
{

}
