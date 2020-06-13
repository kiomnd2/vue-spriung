package com.kiomnd2.vuespring.controller;


import com.kiomnd2.vuespring.dto.ListDto;
import com.kiomnd2.vuespring.dto.MemberDto;
import com.kiomnd2.vuespring.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/list")
@RestController
public class ListController {

    final ListService listService;


    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping("getAll")
    public List<ListDto> getList(@RequestBody MemberDto member) {
        return  listService.getTodoList(member);
    }

    @PostMapping("/register")
    public void registerList(@RequestBody ListDto listDto) {
        listService.registerItem(listDto);
    }

    @PostMapping("/update")
    public void updateList(@RequestBody ListDto listDto) { }




}
