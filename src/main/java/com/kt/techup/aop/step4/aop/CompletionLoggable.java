package com.kt.techup.aop.step4.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * [Step 4: AOP - CompletionLoggable 어노테이션]
 * 메서드 실행 완료 후 로깅 부가 기능을 적용할 대상임을 선언하는 마커 어노테이션입니다.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CompletionLoggable {
}
