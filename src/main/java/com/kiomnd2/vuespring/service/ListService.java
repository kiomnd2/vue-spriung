package com.kiomnd2.vuespring.service;


import com.kiomnd2.vuespring.dto.ListDto;
import com.kiomnd2.vuespring.entity.ListEntity;
import com.kiomnd2.vuespring.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListService {

    final
    ListRepository listRepository;

    public ListService(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public List<ListDto> getTodoList(Long id) {
        List<ListEntity> listEntities = listRepository.findAllById(id);
        List<ListDto> listDtos = new ArrayList<>();


        listEntities.forEach(listEntity -> {
            listDtos.add(
                    ListDto.builder()
                            .id(listEntity.getId())
                            .contents(listEntity.getContents())
                            .isComplete(listEntity.isComplete())
                            .regDate(listEntity.getRegDate())
                            .updateDate(listEntity.getUpdateDate())
                            .build()
            );
        });

        return listDtos;
    }
}
