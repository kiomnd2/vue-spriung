package com.kiomnd2.vuespring.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    private long id;

    @Column( length = 50, nullable = false, unique = true)
    private String userId;

    @Column( length = 100)
    private String userNm;
    @Column( length = 200)
    private String email;
    @Column( length = 20)
    private String password;
    @Column( length =14)

    @CreationTimestamp
    private LocalDateTime regDate;

    @CreationTimestamp
    private LocalDateTime updateDate;



}

