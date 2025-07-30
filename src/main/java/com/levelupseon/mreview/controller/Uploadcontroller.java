package com.levelupseon.mreview.controller;

import com.levelupseon.mreview.dto.UploadDTO;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Log4j2
@Controller // @Controller + @ResponseBody (즉, JSON 응답)
public class Uploadcontroller {
  @Value("${spring.servlet.multipart.location}")
  private String uploadPath ;

  @PostMapping("uploadAjax")
  public @ResponseBody ResponseEntity<?> uploadAjax(MultipartFile[] files) {
    return ResponseEntity.ok(Arrays.stream(files).map(f -> {
      String uuid = null;
      String folderPath = null;
      log.info(f.getOriginalFilename());
      try {
        //이미지만 업로드 가능
        if(! f.getContentType().startsWith("image/")) {
          log.warn(f.getContentType() + "is not image");
          return ResponseEntity.badRequest().build();
        }
        log.info(f.getOriginalFilename());
        folderPath  = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        uuid = UUID.randomUUID().toString();
        folderPath = uploadPath + "/" + folderPath;
        String saveName = uuid + "_" + f.getOriginalFilename();

        new File(folderPath).mkdirs();

        //파일 저장
        f.transferTo(new File(uploadPath, saveName));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return Map.of("origin", f.getOriginalFilename(), "size", f.getSize(), "uuid", uuid, "path", folderPath);
    }).toList()); // List<Map<String,Object>> 형태로 변환
  }

  @GetMapping("uploadEx")
  public void uploadEx() {
  }
  private String genPath() {
    return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
  }
}
