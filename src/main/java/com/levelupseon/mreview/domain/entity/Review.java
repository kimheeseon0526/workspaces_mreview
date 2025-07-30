package com.levelupseon.mreview.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"movie", "member"})
public class Review extends BaseEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reviewnum;

  @ManyToOne(fetch = FetchType.LAZY)
  private Movie movie;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  private int grade;
  private String text;
}
