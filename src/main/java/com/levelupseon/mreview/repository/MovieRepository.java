package com.levelupseon.mreview.repository;

import com.levelupseon.mreview.domain.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
  @Query("Select m, mi, round(avg(coalesce(r.grade, 0)), 1), count(distinct r) from Movie m "
          + "left join  MovieImage mi on mi.movie = m "
          + "left join Review  r on r.movie = m group by m")
  Page<Object[]> getListPage(Pageable pageable);  //object 배열 길이 두개

  @Query("select m, mi, round(avg(coalesce(r.grade, 0)), 1), count(distinct r) from Movie m "
        + "join MovieImage mi on mi.movie = m"
        + " left join Review  r on r.movie = m where m.mno = :mno group by mi")
  List<Object[]> getMovieWithAll(@Param("mno") Long mno);
}
