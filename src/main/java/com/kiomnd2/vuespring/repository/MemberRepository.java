package com.kiomnd2.vuespring.repository;

import com.kiomnd2.vuespring.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member,Long> {
    Optional<Member> findById(String id);
}