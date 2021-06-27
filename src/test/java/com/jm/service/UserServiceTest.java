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
                .nickName("고래만두")
                .role(Role.USER)
                .build();

        // when
        Long userId = userService.join(requestDto);

        // then
        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        assertEquals(requestDto.getEmail(), user.getEmail(), "같은 이메일이어야 한다");
        assertEquals("soato1405", user.getPublicUserId(), "이메일의 아이디가 유저의 공개 아이디로 자동 설정된다.");
        assertThat(user.getEmail()).isEqualTo(requestDto.getEmail());
    }

    @Test
    public void 중복_이메일_회원_가입() throws Exception {
        // given
        UserJoinRequestDto requestDto1 = UserJoinRequestDto.builder()
                .name("서정민")
                .address(new Address("Incheon", "마장로", "264번길66"))
                .email("soato1405@gmail.com")
                .nickName("고래만두")
                .role(Role.USER)
                .build();
        UserJoinRequestDto requestDto2 = UserJoinRequestDto.builder()
                .name("최재근")
                .address(new Address("Incheon", "마장로", "264번길66"))
                .email("soato1405@gmail.com")
                .nickName("헬루움")
                .role(Role.USER)
                .build();

        // when
        Long user1Id = userService.join(requestDto1);

        // then
        assertThrows(DuplicateEmailMemberExitException.class, () -> userService.join(requestDto2));
    }
}