package com.levelupseon.mreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieImageDTO {
  private Long mno;
  private String origin;
  private String uuid;
  private String path;

  public String getUrl() { // 여기서 사용위치
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    builder.queryParam("origin", origin);
    builder.queryParam("uuid", uuid);
    builder.queryParam("path", path);
    return builder.build().toUriString();
  }

  public String getThumb() {
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    builder.queryParam("origin", origin);
    builder.queryParam("uuid", "s_" + uuid);
    builder.queryParam("path", path);
    return builder.build().toUriString();
  }

}
