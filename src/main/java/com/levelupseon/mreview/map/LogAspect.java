package com.levelupseon.mreview.map;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Log4j2
@Component
public class LogAspect {
  @Before("execution(* *..*.ReviewController.*(..))")
  //메서드 실행전에 advice 실행, 클래스명 *ReviewController로 끝나는 모든 클래스
  //메서드 정의 joinPoint = 메서드 실행 지점
  public void beforeLog(JoinPoint joinPoint) {
    log.info("------------------------------" + joinPoint.getSignature().getName());
    Arrays.stream(joinPoint.getArgs()).forEach(log::info);  //배열 타입
  }

}
