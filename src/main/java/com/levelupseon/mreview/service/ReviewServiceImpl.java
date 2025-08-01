package com.levelupseon.mreview.service;

import com.levelupseon.mreview.domain.dto.ReviewDTO;
import com.levelupseon.mreview.domain.entity.Review;
import com.levelupseon.mreview.repository.ReviewRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public non-sealed class ReviewServiceImpl implements ReviewService{
  private final ReviewRepository repository;
  @Override
  public List<ReviewDTO> getListWithMovie(Long mno) {
    return repository.findByMovie_mno(mno).stream().map(this::toDTO).toList();
  }

  @Override
  public Long register(ReviewDTO dto) {
    return repository.save(toEntity(dto)).getReviewnum();
  }

  @Override
  public void modify(ReviewDTO dto) {
    Review review = repository.findById(dto.getReviewnum()).orElseThrow(() -> new IllegalArgumentException("Review Not Found"));
    review.setGrade(dto.getGrade());
    review.setText(dto.getText());

  }

  @Override
  public void remove(Long reviewnum) {
    repository.deleteById(reviewnum);

  }
}
