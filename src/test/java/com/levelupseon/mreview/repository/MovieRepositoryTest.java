package com.levelupseon.mreview.repository;

import com.levelupseon.mreview.domain.entity.Movie;
import com.levelupseon.mreview.domain.entity.MovieImage;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MovieRepositoryTest {
  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private MovieImageRepository imageRepository;

  @Test
  public void testExist() {
    Assertions.assertNotNull( movieRepository);
  }

  @Test
  @Commit
  @Transactional
  public void insertMovies() {
    IntStream.rangeClosed(1, 100).forEach( i -> {
      Movie movie =  Movie.builder().title("제목" + i).build();
      movieRepository.save(movie);

      int count = (int)(Math.random() * 5) + 1;

      for(int j = 0; j < count; j++) {
        MovieImage movieImage = MovieImage.builder()
                .uuid(UUID.randomUUID().toString())
                .movie(movie)
                .imgName("test" + j + ".jpg")
                .build();

        imageRepository.save(movieImage);
      }
    });
  }

  //페이지 처리되는 영화별 리뷰 갯수 구하기(368p)
  @Test
  public void testGetListPage() {
    PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
    Page<Object[]> listPage = movieRepository.getListPage(pageRequest);
    for(Object[] o :  listPage.getContent()) {
      log.info(Arrays.toString(o));
    }
  }

  //100번 게시글의 모든 이미지 출력(100번 기준 3개)
  @Test
  public void TestGEetMovieWithAll() {
    movieRepository.getMovieWithAll(100L).forEach(m -> log.info(Arrays.toString(m)));
  }

}
