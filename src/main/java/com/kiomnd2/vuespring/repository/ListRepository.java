package com.kiomnd2.vuespring.repository;

import com.kiomnd2.vuespring.dto.ListDto;
import com.kiomnd2.vuespring.entity.ListEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListRepository extends CrudRepository<ListEntity,Long> {
     List<ListEntity> findAllById(Long id);
}
