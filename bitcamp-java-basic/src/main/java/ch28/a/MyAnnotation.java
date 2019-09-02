package ch28.a;

// 애노테이션 정의
// 
public @interface MyAnnotation {

}

//한줄주석  :컴파일 할때 .class파일에 포함되지않음
// 소스코드에 설명을 붙일 때 사용 
/*
 * 
여러줄주석 
 :소스코드에 설명을 붙일때 사용 
 : 컴파일 할때 .class파일에 포함되지않음
*/
/**
  javadoc 주석
  : javadoc.exe 를 통해 html api 문서를 생성할때 사용됨
  : 컴파일할때 .class 파일에 포함되지 않음
*/
//  Annotation
// @xxxxx(프로퍼티 = 값, 프로퍼티 = 값 ): 애노테이션
// : 컴파일러에게 정보 전달할때 사용 
// : 실행중에 정보를 전달할떄 사용 . 
// : .class 파일에 포함할수있다

