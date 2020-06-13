package com.kiomnd2.vuespring.repository;

import com.kiomnd2.vuespring.dto.MemberDto;
import com.kiomnd2.vuespring.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface MemberRepository extends CrudRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByUserId(String id);
}