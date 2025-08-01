package com.levelupseon.mreview.controller.unit;

import com.levelupseon.mreview.controller.ReviewController;
import com.levelupseon.mreview.repository.ReviewRepository;
import com.levelupseon.mreview.service.ReviewService;
import com.levelupseon.mreview.service.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ReviewController.class)
@ContextConfiguration(name = "application.yml")
public class ReviewControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ReviewServiceImpl service;
  @MockBean
  private ReviewRepository repository;

  @Test
  public void 단순_목록_조회() throws Exception {
    long mno = 5L;
    mockMvc.perform(MockMvcRequestBuilders.get(String.format("/review/%d", mno)));
  }
}
