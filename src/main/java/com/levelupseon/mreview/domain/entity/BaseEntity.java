package com.levelupseon.mreview.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@EqualsAndHashCode(exclude = "modDate")
public abstract class BaseEntity {
  @CreatedDate
  @Column(updatable = false, name= "regdate")
  private LocalDateTime regDate;

  @LastModifiedDate
  @Column(name = "moddate")
  private LocalDateTime modDate;
}
