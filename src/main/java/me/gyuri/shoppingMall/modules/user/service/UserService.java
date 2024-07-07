package me.gyuri.shoppingMall.modules.user.service;

import lombok.RequiredArgsConstructor;
import me.gyuri.shoppingMall.modules.user.domain.User;
import me.gyuri.shoppingMall.modules.user.dto.CreateUserRequest;
import me.gyuri.shoppingMall.modules.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(CreateUserRequest request) {
        return userRepository.save(User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build()).getId();
    }
}
