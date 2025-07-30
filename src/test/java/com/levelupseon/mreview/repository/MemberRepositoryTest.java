package com.levelupseon.mreview.repository;

import com.levelupseon.mreview.domain.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTest {
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private ReviewRepository reviewRepository;

  @Test
  public void testExist() {
    Assertions.assertNotNull( memberRepository);
  }

  @Test
  public void insertMember() {
    IntStream.rangeClosed(1, 100).forEach( i -> {
      Member member = Member.builder()
              .email("r" + i + "@zerock.org")
              .pw("1111")
              .nickname("reviewer" + i)
              .build();
      memberRepository.save(member);
    });
  }
  @Test
  @Transactional
  @Commit
  public void testDeleteByMembermid() {
//    reviewRepository.deleteByMember_mid(15L); //97번의 리뷰부터 삭제
    memberRepository.deleteById(15L); //리뷰 삭제 후 아이디 삭제
    //외래키 삭제시 cascade 설정하면 15번 게시글의 아이디를 삭제하면 그 리뷰까지 한 번에 삭제됨
  }
}
