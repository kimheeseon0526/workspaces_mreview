package com.levelupseon.mreview.service;

import com.levelupseon.mreview.domain.dto.MovieDTO;
import com.levelupseon.mreview.domain.dto.MovieImageDTO;
import com.levelupseon.mreview.domain.dto.PageRequestDTO;
import com.levelupseon.mreview.domain.dto.PageResponseDTO;
import com.levelupseon.mreview.domain.entity.Movie;
import com.levelupseon.mreview.domain.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public sealed interface MovieService permits MovieServiceImpl {
  Long register(MovieDTO dto);
  PageResponseDTO<MovieDTO, Object[]> getList(PageRequestDTO dto);

  default Map<String, Object> toEntitiy(MovieDTO dto){
    Map<String, Object> map = new HashMap<>();
    Movie movie = Movie.builder().title(dto.getTitle()).mno(dto.getMno()).build();
    map.put("movie", movie);
    map.put("images", dto.getList().stream().map( m ->
            MovieImage.builder()
                    .movie(Movie.builder().mno(dto.getMno()).build())
                    .uuid(m.getUuid())
                    .path(m.getPath())
                    .imgName(m.getOrigin())
                    .build()).toList());

    return map;
  }

  default MovieDTO toDTO(Movie movie, List<MovieImage> images, double avg, long reviewCnt) {
    return MovieDTO.builder()
            .mno(movie.getMno())
            .title(movie.getTitle())
            .regDate(movie.getRegDate())
            .modDate(movie.getModDate())
            .list(images.stream().map(i -> i == null ? null : MovieImageDTO.builder()
                    .origin(i.getImgName())
                    .uuid(i.getUuid())
                    .path(i.getPath())
                    .build()).toList())
            .avg(avg)
            .reviewCnt(reviewCnt)
            .build();
  }


  MovieDTO get(Long mno);
}
