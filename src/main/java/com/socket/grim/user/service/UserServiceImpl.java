package com.socket.grim.user.service;

import com.socket.grim.user.dto.UserCreateRequestDto;
import com.socket.grim.user.dto.UserResponseDto;
import com.socket.grim.user.entity.User;
import com.socket.grim.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;


  @Override
  public void createUser(UserCreateRequestDto userCreateRequestDto) {
    log.info("User Create Request : {}", userCreateRequestDto);
    verifyExistsEmail(userCreateRequestDto.getEmail());

    String encryptedPassword = passwordEncoder.encode(userCreateRequestDto.getPassword());

    User createdUser = getBuildUser(userCreateRequestDto, encryptedPassword);

    userRepository.save(createdUser);
  }

  @Override
  public Optional<UserResponseDto> findUserByUserId(Long userId) throws Exception {
    Optional<User> optionalUser = userRepository.findById(userId); // findById 메소드 사용 (수정)
    User findUser = optionalUser.orElseThrow(() -> new Exception("User not found for userId: " + userId)); // Optional 에서 User 추출, 예외 처리 (orElseThrow() 로 존재 여부 검증 및 예외 처리)

    return Optional.of(new UserResponseDto(findUser));
  }

  @Override
  public Optional<UserResponseDto> findUserByEmail(String email) {
    return Optional.empty();
  }

  @Override
  public List<UserResponseDto> findAllUsers() {
    return List.of();
  }

  @Override
  public void updateUser(UserCreateRequestDto userCreateRequestDto, Long userId) {

  }

  @Override
  public void updateDormantUser(Long userId) {

  }

  @Override
  public void updateWithdrawUser(Long userId) {

  }

  @Override
  public void deleteUser(Long userId) {

  }

  private User getBuildUser(UserCreateRequestDto userCreateRequestDto, String encryptedPassword) {
    return User.builder()
            .name(userCreateRequestDto.getName())
            .email(userCreateRequestDto.getEmail())
            .password(encryptedPassword)
            .nickname(userCreateRequestDto.getNickname())
            .phoneNumber(userCreateRequestDto.getPhoneNumber())
            .build();
  }

  private void verifyExistsEmail(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
    }
  }

  private Boolean checkPassword(User author, String password) {
    String originPassword = author.getPassword();

    if(passwordEncoder.matches(password, originPassword)) {
      return true;
    }

    else return false;
  }
}
