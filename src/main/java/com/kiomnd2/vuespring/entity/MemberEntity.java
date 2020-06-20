package com.kiomnd2.vuespring.entity;

import com.kiomnd2.vuespring.dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "member")
public class MemberEntity extends TimeEntity{

    @Id @GeneratedValue
    private long id;

    @Column( length = 50, nullable = false, unique = true)
    private String userId;

    @Column( length = 100)
    private String userNm;
    @Column( length = 200)
    private String email;
    @Column( length = 200)
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private Set<ListEntity> lists = new HashSet<>();


    public void addItem(ListEntity item) {
        item.setMember(this);
        lists.add(item);
    }

    // 뭔가 변경 필요한 속성에 대해 메서드를 생성해 줘야함

    @Builder
    public MemberEntity(final long id, final String userId, final String userNm, final String email, final String password, Set<ListEntity> lists)
    {
        this.id = id;
        this.userId = userId;
        this.userNm = userNm;
        this.email = email;
        this.password = password;
        this.lists = lists;
    }

    public MemberDto toDto(){
        return MemberDto.builder()
                .id(this.id)
                .userNm(this.userNm)
                .userId(this.userId)
                .email(this.getEmail())
                .password(this.password)
                .regDate(this.getRegDate())
                .updateDate(this.getUpdateDate())
                .build();
    }


}

