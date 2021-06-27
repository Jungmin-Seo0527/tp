package com.jm.domain.user;

import com.jm.domain.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String nickName;

    private String email;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String name, String nickName, String email, Address address, Role role) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.address = address;
        this.role = role;
    }

    public User updateNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
