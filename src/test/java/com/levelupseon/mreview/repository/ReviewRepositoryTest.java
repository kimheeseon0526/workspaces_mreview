package com.levelupseon.mreview.repository;

import com.levelupseon.mreview.domain.entity.Member;
import com.levelupseon.mreview.domain.entity.Movie;
import com.levelupseon.mreview.domain.entity.Review;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReviewRepositoryTest {
  @Autowired
  private ReviewRepository reviewRepository;

  @Test
  public void testExist() {
    Assertions.assertNotNull(reviewRepository);
  }

  @Test
  @Transactional
  public void insertReviews() {
    //200개 리뷰 등록
    IntStream.rangeClosed(1, 200).forEach( i -> {
      //영화번호
      Long mno = (long)(Math.random() * 100) + 1;
      //리뷰어 번호
      Long mid = ((long)(Math.random() * 100) + 1);
      Member member = Member.builder().mid(mid).build();

      Review movieReview = Review.builder()
              .member(member)
              .movie(Movie.builder().mno(mno).build())
              .grade((int)(Math.random() * 5) + 1)
              .text("이 영화 느낌쓰" + i)
              .build();
      reviewRepository.save(movieReview);
    });
  }

  @Test //380p
//  @Transactional(readOnly = true)
  public void testFindByMovieMno() {
    reviewRepository.findByMovie_mno(97L).forEach(r -> {
      log.info(r);
      log.info(r.getMember().getEmail());
//      log.info(r.getMovie().getTitle());
      //LazyInitializationException 에러 발생
      // ->1. @Transactional(readOnly = true) 사용 : 댓글수 + 1 만큼 쿼리문 조회됨
      // ->2. @EntityGraph 사용(reviewrepository에) : 딱 한 번
      //실제 쿼리문이 movie 와 review 만 조인했기 때문에 member에 그냥 접근하면 터지고
      //Transactional 사용하여 조회할 때에만 직접 접근할 수 있게 처리
      //댓글수 + 1 만큼 쿼리문 조회됨
    });
  }

}
