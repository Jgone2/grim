package common;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA 엔티티 클래스들의 공통 속성을 관리하는 기반 클래스임을 나타냄 -> 테이블로 생성되지 않음
@EntityListeners(AuditingEntityListener.class) // JPA Auditing -> 엔티티의 특정 이벤트(생성, 수정)가 발생할 때 자동으로 동작하도록 설정
public abstract class BaseTime {

  @CreatedDate
  @Setter(lombok.AccessLevel.NONE)
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;
}
