package com.jm.web.dto;

import com.jm.domain.user.Address;
import com.jm.domain.user.Role;
import com.jm.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinRequestDto {
    private String name;
    private String nickName;
    private String email;
    private Address address;
    private Role role;

    @Builder
    public UserJoinRequestDto(String name, String nickName, String email, Address address, Role role) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.address = address;
        this.role = role;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .nickName(nickName)
                .email(email)
                .address(address)
                .role(role)
                .build();
    }
}
