package com.levelupseon.mreview.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Movie extends BaseEntity{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mno;

  private String title;

}
