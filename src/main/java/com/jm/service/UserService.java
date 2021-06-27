package com.jm.service;

import com.jm.web.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    public void updateNickName(UserUpdateDto userUpdateDto) {

    }
}
