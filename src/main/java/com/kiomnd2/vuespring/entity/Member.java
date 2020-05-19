package com.kiomnd2.vuespring.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
@Entity
@NoArgsConstructor
@Table(name="Member")
public class Member {

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

    @CreationTimestamp
    private LocalDateTime regDate;

    @CreationTimestamp
    private LocalDateTime updateDate;


    @Builder
    public Member(final String userId, final String userNm, final String email, final String password)
    {
        this.userId = userId;
        this.userNm = userNm;
        this.email = email;
        this.password = password;
    }


}

