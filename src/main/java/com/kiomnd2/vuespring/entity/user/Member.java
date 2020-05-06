package com.kiomnd2.vuespring.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Member {

    @Id
    private long id;

    @Column( length = 100 ,unique = true)
    private String name;
    @Column( length = 20)
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Member() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id &&
                Objects.equals(name, member.name) &&
                Objects.equals(password, member.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }
}

