package me.gyuri.shoppingMall.modules.user.repository;

import me.gyuri.shoppingMall.modules.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
