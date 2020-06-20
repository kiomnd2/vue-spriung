package com.kiomnd2.vuespring.service;


import com.kiomnd2.vuespring.dto.ListDto;
import com.kiomnd2.vuespring.dto.MemberDto;
import com.kiomnd2.vuespring.entity.ListEntity;
import com.kiomnd2.vuespring.entity.MemberEntity;
import com.kiomnd2.vuespring.repository.ListRepository;
import com.kiomnd2.vuespring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ListService {

    final
    ListRepository listRepository;

    final MemberRepository memberRepository;

    public ListService(ListRepository listRepository, MemberRepository memberRepository) {
        this.listRepository = listRepository;
        this.memberRepository = memberRepository;
    }

    public Set<ListDto> getTodoList(MemberDto member) {
        MemberEntity memberEntity = memberRepository.findByUserId(member.getUserId()).get();

        Set<ListEntity> lists = memberEntity.getLists();

        Set<ListDto> listDtos = new HashSet<>();

        lists.forEach(listEntity -> {
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
        MemberEntity memberEntity = memberRepository.findByUserId(listDto.getMemberDto().getUserId()).get();


        ListEntity listEntity = ListEntity.builder()
                .contents(listDto.getContents())
                .isComplete(listDto.isComplete())
                .member(memberEntity)
                .build();

        ListEntity le =listRepository.save(listEntity);
        return ListDto.builder()
                .isComplete(le.isComplete())
                .contents(le.getContents())
                .regDate(listEntity.getRegDate())
                .updateDate(listEntity.getUpdateDate())
                .id(le.getId()).build();
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
