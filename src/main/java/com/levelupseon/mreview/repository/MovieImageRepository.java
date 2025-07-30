package com.levelupseon.mreview.repository;

import com.levelupseon.mreview.domain.entity.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
