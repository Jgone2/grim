package com.socket.grim.user.repository;

import com.socket.grim.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  /**
   * 이메일로 유저 정보를 조회하는 메서드
   * @param email
   * @return Optional<User>
   */
  Optional<User> findUserByEmail(String email); // Optional을 사용하면 null 체크를 하지 않아도 된다. NullPointException을 방지할 수 있다.

  Boolean existsByEmail(String email);

  User findUserByUserId(Long userId);
}
