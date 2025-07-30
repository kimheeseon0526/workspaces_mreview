package com.levelupseon.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadDTO {
  private String uuid;
  private String origin;
  private String path;
  private long size;
  private boolean image;
  private int odr;

}
