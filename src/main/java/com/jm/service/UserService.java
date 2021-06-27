package com.jm.service;

import com.jm.domain.user.User;
import com.jm.domain.user.UserRepository;
import com.jm.exception.DuplicateEmailMemberExitException;
import com.jm.web.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 가입
     * @param requestDto
     * @return user_id (pk)
     */
    // TODO: 2021-06-28 중복되는 닉네임 예외 처리
    public Long join(UserJoinRequestDto requestDto) {
        Optional<User> sameEmailUser = userRepository.findByEmail(requestDto.getEmail());
        if (sameEmailUser.isPresent()) {
            throw new DuplicateEmailMemberExitException("이메일이 중복되는 회원이 존재합니다.");
        }
        return userRepository.save(requestDto.toEntity()).getId();
    }
}
