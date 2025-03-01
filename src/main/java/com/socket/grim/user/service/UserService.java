package com.socket.grim.user.service;

import com.socket.grim.user.dto.UserCreateRequestDto;
import com.socket.grim.user.dto.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

  // User 생성
  void createUser(UserCreateRequestDto userCreateRequestDto);

  // User 조회
  Optional<UserResponseDto> findUserByUserId(Long userId) throws Exception;
  Optional<UserResponseDto> findUserByEmail(String email);

  List<UserResponseDto> findAllUsers();

  // User 수정
  void updateUser(UserCreateRequestDto userCreateRequestDto, Long userId);


  // 휴면 계정으로 전환
  void updateDormantUser(Long userId);

  // 탈퇴 회원으로 전환
  void updateWithdrawUser(Long userId);

  // User 삭제
  void deleteUser(Long userId);
}
