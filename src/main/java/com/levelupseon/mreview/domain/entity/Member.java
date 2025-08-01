package com.levelupseon.mreview.domain.entity;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "m_member")
public class Member extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mid;
  private Long mno;

  private String email;
  private String pw;
  private String nickname;

}
