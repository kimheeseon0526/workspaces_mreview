package com.levelupseon.mreview.controller;

import com.levelupseon.mreview.domain.dto.ReviewDTO;
import com.levelupseon.mreview.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("review")
@Tag(name = "Review API", description = "영화와 관계된 리뷰를 작성할 수 있는 요청 클래수")
public class ReviewController {
  private final ReviewService service;

  @Operation(summary = "영화 하나에 작성된 리뷰 조회하는 호출", description = "필 수 파라미터로 영화번호(mno) 요구")
  @GetMapping("{mno}/all")
  public ResponseEntity<?> list(@PathVariable Long mno) {
    return ResponseEntity.ok(service.getListWithMovie(mno));
  }

  @PostMapping("{mno}")
  public ResponseEntity<?> create(@PathVariable Long mno, @RequestBody ReviewDTO dto) {
    return ResponseEntity.ok(service.register(dto));
  }

  @PutMapping("{mno}/{reviewnum}")
  public ResponseEntity<?> update(@PathVariable Long mno, @PathVariable Long reviewnum, @RequestBody ReviewDTO dto) {
    service.modify(dto);
    return ResponseEntity.ok(reviewnum);
  }
  @DeleteMapping("{mno}/{reviewnum}")
  public ResponseEntity<?> delete(@PathVariable Long mno, @PathVariable Long reviewnum) {
    service.remove(reviewnum);
    return ResponseEntity.ok(reviewnum);
  }


}
