package com.kiomnd2.vuespring.jpa;

import com.kiomnd2.vuespring.dto.ListDto;
import com.kiomnd2.vuespring.dto.MemberDto;
import com.kiomnd2.vuespring.entity.ListEntity;
import com.kiomnd2.vuespring.repository.ListRepository;
import com.kiomnd2.vuespring.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ListJpaTest {

    @Autowired
    private ListRepository repository;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void regTest() throws  Exception {
        MemberDto member = MemberDto.builder().userId("kiomnd2").password("qwer1234").email("kiomnd2@naver.com").build();
        final ListDto listDto = ListDto.builder().contents("aaaaaa").memberDto(member).isComplete(false).build();
        Long id = repository.save(listDto.toEntity()).getId();

        Assert.assertThat(id, is(notNullValue()));
    }

    @Test
    public void 등록하고_아이디로_찾고() throws Exception {
        MemberDto member = MemberDto.builder().userId("kiomnd2").password("qwer1234").email("kiomnd2@naver.com").build();
        final ListDto listDto = ListDto.builder().contents("aaaaaa").memberDto(member).isComplete(false).build();
        Long id = repository.save(listDto.toEntity()).getId();
        ListEntity entity = repository.findById(id).get();
        Assert.assertThat(id, is(entity.getId()));
    }


    @Test
    public void 멤버로_찾기() throws Exception {
        // 멤버 등록
        MemberDto member = MemberDto.builder().userNm("이름").userId("kiomnd2").password("qwer1234").email("kiomnd2@naver.com").build();
//        memberRepository.save(member.toEntity());

        final ListDto listDto = ListDto.builder().contents("aaaaaa").memberDto(member).isComplete(false).build();
        String userId = repository.save(listDto.toEntity()).getMember().getUserId();
        List<ListEntity> list = repository.findAllByMember_UserId(userId);
        Assert.assertThat(member.getUserId(), is(list.get(0).getMember().getUserId()));
    }


    @Test
    public void 등록한_리스트_업데이트() throws Exception {
        MemberDto member = MemberDto.builder().userNm("이름").userId("kiomnd2").password("qwer1234").email("kiomnd2@naver.com").build();
//        memberRepository.save(member.toEntity());

        // 등록하고
        final ListDto listDto = ListDto.builder().contents("aaaaaa").memberDto(member).isComplete(false).build();
        long id = repository.save(listDto.toEntity()).getId();

        // 가져와서 업데이트
        ListEntity entity = repository.findById(id).get();
        ListDto updatedDto = listDto.builder().contents("DDDDD").build();
        ListEntity updatedEntity = entity.update(updatedDto);
        Long updatedId = repository.save(updatedEntity).getId();

        //다시 가져옴
        ListEntity findList =repository.findById(updatedId).get();


        Assert.assertThat(id, is(updatedId));
        Assert.assertThat("DDDDD", is(findList.getContents()));

    }



}
