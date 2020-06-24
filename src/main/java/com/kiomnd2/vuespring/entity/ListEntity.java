package com.kiomnd2.vuespring.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.kiomnd2.vuespring.dto.ListDto;
import com.kiomnd2.vuespring.dto.MemberDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@ToString
@Getter
@NoArgsConstructor
@Entity
@Table(name = "list")
public class ListEntity extends TimeEntity{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private MemberEntity member;

    @Column(length = 500)
    private String contents;

    @Column
    private boolean complete;


    public ListEntity update(ListDto listDto) {
        this.contents = listDto.getContents();
        this.complete = listDto.isComplete();
        return this;
    }

    public void setMember(MemberEntity member){
        this.member = member;
    }

    @Builder
    public ListEntity(String contents, MemberEntity member, boolean complete) {
        this.member = member;
        this.contents = contents;
        this.complete = complete;
    }

}
