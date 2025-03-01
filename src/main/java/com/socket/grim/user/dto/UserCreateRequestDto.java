package com.socket.grim.user.dto;

import com.socket.grim.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateRequestDto {

  private String name;
  private String email;
  private String password;
  private String nickname;
  private String phoneNumber;

  @Builder
  public UserCreateRequestDto(String name, String email, String password, String nickname, String phoneNumber) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.phoneNumber = phoneNumber;
  }

  public User toEntity(UserCreateRequestDto userCreateRequestDto) {
    return User.builder()
            .name(userCreateRequestDto.getName())
            .email(userCreateRequestDto.getEmail())
            .password(userCreateRequestDto.getPassword())
            .nickname(userCreateRequestDto.getNickname())
            .phoneNumber(userCreateRequestDto.getPhoneNumber())
            .build();
  }
}
