package ch28.e;

//선택 프로퍼티 
// => 애노테이션을 사용할떄 값을 설정하지않아도된다 .
// => 값을 설정하지않으면 기본값이 사용된다 .
public @interface MyAnnotation3 {
  String value() default "오호라"; // 선택 입력
}
