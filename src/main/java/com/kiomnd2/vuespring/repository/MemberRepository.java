package com.kiomnd2.vuespring.repository;

import com.kiomnd2.vuespring.dto.MemberDto;
import com.kiomnd2.vuespring.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;


public interface MemberRepository extends CrudRepository<MemberDto,Long> {
    MemberDto findById(String id);
}