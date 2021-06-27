package com.jm.service;

import com.jm.domain.user.Address;
import com.jm.domain.user.Role;
import com.jm.domain.user.User;
import com.jm.domain.user.UserRepository;
import com.jm.exception.DuplicateEmailMemberExitException;
import com.jm.web.dto.UserJoinRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void 회원_가입() throws Exception {
        // given
        UserJoinRequestDto requestDto = UserJoinRequestDto.builder()
                .name("서정민")
                .address(new Address("Incheon", "마장로", "264번길66"))
                .email("soato1405@gmail.com")
                .nickName("ㅋㅋㅋ")
                .role(Role.USER)
                .build();

        // when
        Long userId = userService.join(requestDto);

        // then
        Optional<User> user = userRepository.findByEmail(requestDto.getEmail());
        assertEquals(requestDto.getEmail(), user.orElseThrow().getEmail(), "같은 이메일이어야 한다");
        assertThat(user.orElseThrow(NullPointerException::new).getEmail()).isEqualTo(requestDto.getEmail());
    }

    @Test
    public void 중복_이메일_회원_가입() throws Exception {
        // given
        UserJoinRequestDto requestDto1 = UserJoinRequestDto.builder()
                .name("서정민")
                .address(new Address("Incheon", "마장로", "264번길66"))
                .email("soato1405@gmail.com")
                .nickName("ㅋㅋㅋ")
                .role(Role.USER)
                .build();
        UserJoinRequestDto requestDto2 = UserJoinRequestDto.builder()
                .name("고래균")
                .address(new Address("Incheon", "마장로", "264번길66"))
                .email("soato1405@gmail.com")
                .nickName("ㅋㅋㅋ")
                .role(Role.USER)
                .build();

        // when
        Long user1Id = userService.join(requestDto1);

        // then
        assertThrows(DuplicateEmailMemberExitException.class, () -> userService.join(requestDto2));
    }
}