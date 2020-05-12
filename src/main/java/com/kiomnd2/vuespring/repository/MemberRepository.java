package com.kiomnd2.vuespring.repository;

import com.kiomnd2.vuespring.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {

}
