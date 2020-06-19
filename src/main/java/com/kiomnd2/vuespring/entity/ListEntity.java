package com.kiomnd2.vuespring.entity;


import com.kiomnd2.vuespring.dto.ListDto;
import com.kiomnd2.vuespring.dto.MemberDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@EqualsAndHashCode
@ToString
@Getter
@NoArgsConstructor
@Entity
@Table(name = "list")
public class ListEntity extends TimeEntity{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = MemberEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_userId")
    private MemberEntity member;

    @Column(length = 500)
    private String contents;

    @Column
    private boolean isComplete;


    public ListEntity update(ListDto listDto) {
        this.contents = listDto.getContents();
        this.isComplete = listDto.isComplete();

        return this;
    }

    @Builder
    public ListEntity(String contents, MemberEntity member, boolean isComplete) {
        this.member = member;
        this.contents = contents;
        this.isComplete = isComplete;
    }

}
