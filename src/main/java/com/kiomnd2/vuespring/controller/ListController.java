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

    @PostMapping("/getAll")
    public List<ListDto>  getAll(@RequestBody MemberDto member) {
        System.out.println(member.toString());
        return  listService.getTodoList(member);
    }

    @PostMapping("/register")
    public ListDto registerItem(@RequestBody ListDto listDto) {
        System.out.println(listDto);
        return listService.registerItem(listDto);
    }

    @PostMapping("/update")
    public void updateItem(@RequestBody ListDto listDto) { listService.updateItem(listDto); }


    @PostMapping("/delete")
    public void deleteItem(@RequestBody ListDto listDto) {
        listService.deleteItem(listDto);
    }

}
