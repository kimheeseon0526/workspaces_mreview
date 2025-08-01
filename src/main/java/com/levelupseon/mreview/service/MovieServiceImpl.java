package com.levelupseon.mreview.service;

import com.levelupseon.mreview.domain.dto.MovieDTO;
import com.levelupseon.mreview.domain.dto.PageRequestDTO;
import com.levelupseon.mreview.domain.dto.PageResponseDTO;
import com.levelupseon.mreview.domain.entity.Movie;
import com.levelupseon.mreview.domain.entity.MovieImage;
import com.levelupseon.mreview.repository.MovieImageRepository;
import com.levelupseon.mreview.repository.MovieRepository;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Data
@Log4j2
public non-sealed class MovieServiceImpl implements MovieService{
  private final MovieRepository movieRepository;
  private final MovieImageRepository movieImageRepository;

  @Override
  @Transactional
  public Long register(MovieDTO dto) {
    Map<String, Object> map = toEntitiy(dto);
    Movie movie = (Movie)map.get("movie");
    movieRepository.save(movie);

    List<MovieImage> list = ((List<MovieImage>)map.get("images"));
//    list.forEach(image -> image.setMovie(movie));
    list.forEach(movieImageRepository::save);
    return movie.getMno();
  }

  @Override
  public PageResponseDTO<MovieDTO, Object[]> getList(PageRequestDTO dto) {
    return new PageResponseDTO<>(movieRepository.getListPage(dto.getPageable(Sort.by(Sort.Direction.DESC, "mno"))),
            arr -> toDTO((Movie) arr[0], (List<MovieImage>) (Arrays.asList((MovieImage) arr[1])), (Double)arr[2], (Long)arr[3]));
  }

  @Override
  public MovieDTO get(Long mno) {
    List<Object[]> list = movieRepository.getMovieWithAll(mno);
    Movie movie = (Movie)list.getFirst()[0];
    List<MovieImage> movieImages = (Arrays.asList((MovieImage) list.getFirst()[1]));
    Double avg = (Double)list.getFirst()[2];
    Long reviewCnt = (Long)list.getFirst()[3];
    return toDTO(movie, movieImages, avg, reviewCnt);
  }

}
