package com.levelupseon.mreview.service;

import com.levelupseon.mreview.domain.dto.MovieImageDTO;
import com.levelupseon.mreview.domain.entity.Movie;
import com.levelupseon.mreview.domain.entity.MovieImage;

public interface MovieImageService {
  default MovieImage toEntity(MovieImageDTO dto) {
    return MovieImage.builder()
            .movie(Movie.builder().mno(dto.getMno()).build())
            .uuid(dto.getUuid())
            .path(dto.getPath())
            .imgName(dto.getOrigin())
            .build();
  }
}
