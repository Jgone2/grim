package com.socket.grim.user.entity;

import com.socket.grim.user.config.UserStatusConverter;
import com.socket.grim.user.dto.UserStatus;
import common.BaseTime;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "userId", callSuper = true) // EqualsAndHashCode 어노테이션 추가, of = "userId" 옵션 지정
public class User extends BaseTime {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long userId;

  @Column(name = "user_name", length = 20, nullable = false, unique = false, updatable = true)
  private String name;

  @Column(name = "user_email", length = 255, nullable = false, unique = true, updatable = false)
  private String email;

  @Column(name = "user_password", length = 255, nullable = false, unique = false, updatable = true)
  private String password;

  @Column(name = "user_nickname", length = 20, nullable = false, unique = true, updatable = true)
  private String nickname;

  @Column(name = "user_phone_number", length = 20, nullable = true, unique = false, updatable = true)
  private String phoneNumber;

  @Column(name = "user_status", nullable = false, updatable = false, columnDefinition = "TINYINT")
  @Convert(converter = UserStatusConverter.class)
  private UserStatus userStatus;

  @Column(name = "profile_image_url", length = 1000, nullable = true, unique = false, updatable = true)
  private String profileImageUrl;

  @Column(name = "profile_image_key", length = 500, nullable = true, unique = false, updatable = true)
  private String profileImageKey;

  @Builder
  public User(String name, String email, String password, String nickname, String phoneNumber) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.phoneNumber = phoneNumber;
    this.userStatus = UserStatus.NORMAL;
  }

  @Builder
  public User(String name, String email, String password, String nickname, String phoneNumber, String profileImageUrl) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.phoneNumber = phoneNumber;
    this.userStatus = UserStatus.NORMAL;
    this.profileImageUrl = profileImageUrl;
  }

  @PrePersist // JPA Entity Listener - Persist 되기 전에 실행
  public void prePersist() {
    if (this.userStatus == null) { // 만약 빌더 패턴에서 userStatus 를 설정하지 않았다면, 여기서 default 값 설정 (안전 장치)
      this.userStatus = UserStatus.NORMAL;
    }
    if (this.profileImageUrl == null) { // profileImageUrl 도 default 값 설정 가능 (선택 사항)
      this.profileImageUrl = "default-profile-image-url"; // 예시: 기본 프로필 이미지 URL
    }
  }

  // Custom Setter
  public void changePassword(String newPassword) {
    this.password = newPassword;
  }

  public void changeUsername(String changeName) {
    this.name = changeName;
  }
}
