package com.weesftw.scaffold.domain.service;

import com.weesftw.scaffold.domain.exception.MemberNotFoundException;
import com.weesftw.scaffold.domain.model.Member;
import com.weesftw.scaffold.domain.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class MemberService
{
    private final MemberRepository repository;

    public List<Member> findAll()
    {
        return repository.findAll();
    }

    public long getCount()
    {
        return repository.count();
    }

    public Member findById(Integer id) throws MemberNotFoundException
    {
        return repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("ID: " + id + " not found."));
    }

    @Transactional
    public Member save(Member member)
    {
        return repository.save(member);
    }

    @Transactional
    public void delete(Member member)
    {
        repository.delete(member);
    }
}
