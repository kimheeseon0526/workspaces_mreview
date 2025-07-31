package com.levelupseon.mreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadResultDTO {
  private String origin;  //파일 이름
  private String uuid;
  private String path;

//UriComponentsBuilder를 사용해서 쿼리 파라미터가 붙은 URL을 만들어서 문자열로 반환해주는 코드
  public String getUrl() {
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    builder.queryParam("origin", origin);
    builder.queryParam("uuid", uuid);
    builder.queryParam("path", path);
    return builder.build().toUriString();
  }

  public String getthumb() {
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    builder.queryParam("origin", origin);
    builder.queryParam("uuid", "s_" + uuid);
    builder.queryParam("path", path);
    return builder.build().toUriString();
  }

}
