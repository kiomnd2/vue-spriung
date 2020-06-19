package com.kiomnd2.vuespring.service;


import com.kiomnd2.vuespring.dto.ListDto;
import com.kiomnd2.vuespring.dto.MemberDto;
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

    public List<ListDto> getTodoList(MemberDto member) {
        List<ListEntity> listEntities = listRepository.findAllByMember_UserId(member.getUserId());
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



    public ListDto registerItem(ListDto listDto) {
        ListEntity listEntity = listRepository.save(listDto.toEntity());
        return ListDto.builder()
                .contents(listEntity.getContents())
                .id(listEntity.getId())
                .isComplete(listEntity.isComplete())
                .regDate(listEntity.getRegDate())
                .updateDate(listEntity.getUpdateDate())
                .build();
    }

    public void deleteItem(ListDto listDto) {
        listRepository.deleteById(listDto.getId());
    }

    public Long updateItem(ListDto listDto) {
        ListEntity list= listRepository.findById(listDto.getId()).get();
        list.update(listDto);
        return listRepository.save(list).getId();
    }
}
