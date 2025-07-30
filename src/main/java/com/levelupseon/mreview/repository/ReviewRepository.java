package com.levelupseon.mreview.repository;

import com.levelupseon.mreview.domain.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
  @EntityGraph(attributePaths = {"member", "movie"}, type = EntityGraph.EntityGraphType.FETCH)
  List<Review> findByMovie_mno(Long mno);
  //@EntityGraph : 특정 로딩 설정만 변경하고 싶을 경우 명시(eager)/ 나머지는 기존처럼 lazy

  @Modifying
  @Query("delete from Review r where r.member.mid = ?1")
  void deleteByMember_mid(Long mid);
}
