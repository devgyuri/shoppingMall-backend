package me.gyuri.shoppingMall.modules.user.service;

import lombok.RequiredArgsConstructor;
import me.gyuri.shoppingMall.modules.user.domain.User;
import me.gyuri.shoppingMall.modules.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }
}
