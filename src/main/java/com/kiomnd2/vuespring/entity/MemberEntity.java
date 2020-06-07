package com.kiomnd2.vuespring.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
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

    private String auth;

    // 뭔가 변경 필요한 속성에 대해 메서드를 생성해 줘야함

    @Builder
    public MemberEntity(final String userId, final String userNm, final String email, final String password, final String auth)
    {
        this.userId = userId;
        this.userNm = userNm;
        this.email = email;
        this.password = password;
        this.auth = auth;
    }


}

