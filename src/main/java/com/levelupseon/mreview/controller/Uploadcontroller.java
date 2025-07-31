package com.levelupseon.mreview.controller;

import com.levelupseon.mreview.domain.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
  public @ResponseBody ResponseEntity<?> uploadAjax(MultipartFile[] files) throws IOException {
    List<UploadResultDTO> list = new ArrayList<>();
    for(MultipartFile f : files){
      //이미지만 업로드 가능 -> 즉 이미지가 아닐 때 먼저 걸러줌(bad request)
      if(! f.getContentType().startsWith("image/")) {
        log.warn(f.getContentType() + "is not image");
        return ResponseEntity.badRequest().body("잘못된 파일 형식임");
      }
      String uuid = UUID.randomUUID().toString();
      String path = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
      String savaPath =  uploadPath + "/" + path;

      new File(savaPath).mkdirs();

      //원본 변경 to webp
      String origin = f.getOriginalFilename().substring(0, f.getOriginalFilename().lastIndexOf(".")) + ".webp";
      String saveName = uuid + "_" + origin;
      ImageIO.write(ImageIO.read(f.getInputStream()), "webp", new File(savaPath, saveName));

      //썸네일 생성
      String thumbName = "s_" + saveName;
      BufferedImage thumbnail = Thumbnails.of(ImageIO.read(f.getInputStream()))
              .size(200, 200)
              .asBufferedImage();

      ImageIO.write(thumbnail, "webp", new File(savaPath, thumbName));
        //파일 저장
//      f.transferTo(new File(savaPath, saveName));
      list.add(UploadResultDTO.builder().origin(origin).uuid(uuid).path(path).build());
    }
    return ResponseEntity.ok(list);

  }
  @GetMapping("display")
  public ResponseEntity<?> display(UploadResultDTO dto) throws IOException {
    File file = new File(uploadPath + "/" + dto.getPath(),dto.getUuid() + "_" + dto.getOrigin());
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", Files.probeContentType(file.toPath()));
    return ResponseEntity.ok().headers(headers).body(Files.readAllBytes(file.toPath()));
  }

  @GetMapping("uploadEx")
  public void uploadEx() {
  }
}
