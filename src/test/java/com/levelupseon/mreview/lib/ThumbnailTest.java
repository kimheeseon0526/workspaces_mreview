package com.levelupseon.mreview.lib;

import net.coobird.thumbnailator.Thumbnails;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SpringBootTest
public class ThumbnailTest {

  @Test
  public void testConvert() throws IOException {
    BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\tj\\Desktop\\image", "dia.png"));

    BufferedImage thumbnail = Thumbnails.of(originalImage)
            .size(200, 200)
            .asBufferedImage();

    ImageIO.write(thumbnail, "webp", new File("C:\\Users\\tj\\Desktop\\image", "dia_output.webp"));
  }

  //원본 이미지 해상도 유지, 파일 형식만 바꾸기
  @Test
  public void testConvert2() throws IOException {
    BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\tj\\Desktop\\image", "1718889054_sample_640×426.bmp"));
    //저 경로에 있는 파일을 읽어서 originalImage에 저장
    ImageIO.write(originalImage, "webp", new File("C:\\Users\\tj\\Desktop\\image", "1718889054_sample_640×426_output.webp"));
    //따로 사이즈 등 변환할 필요 없으니 그냥 파일 형식만 바꿔서 저장

  }
}
