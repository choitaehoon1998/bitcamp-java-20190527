// 바이트 배열에 출력할때 Stream API 사용하기 
package ch22.i.character_stream;

import java.io.StringWriter;

public class Test01_3 {
  public static void main(String[] args) throws Exception {
    // 문자 '가'에서부터 100자 출력한다  
    // Test01.1.java와 Test01_2.java에서 문자를 출력하는 방법이다르다
    // 스트링버퍼에 출력할때는 스트링버퍼를 직접 다루고있다
    // 그러나 데이터에 출력할때는 Stream API 를 사용한다
    // 그래서 데이터를 출력할때 일관성이없다
    // 즉 , 유지보수가 불편하다 . 
    // 이를 해결하기 위해 나온 방법이 
    // 메모리(스트링버퍼)에 출력할떄도 Stream API 를 사용하는것이다 . 

    // 1) 문자를 메모리에 출력할수있도록 바이트 배열 출력 스트림을 준비한다 .
    try(StringWriter out = new StringWriter()){

      // 2) 짝수를 스트링 버퍼 에 출력한다 .
      // => !!! 파일에 출력하는것과 똑같다 .
      // => 단 FileWriter 과 달리 Write() 를 호출하여 값을 출력하면
      //    그값은StringWriter 내부에 보관된다 . 
      for(int i=0,ch='가';i<100; i++,ch++) {
        
        out.write((char)(ch));
    }
      System.out.println("출력완료 ");
      // ByteArrayOutputStream 에 저장된값을 꺼내보자 
     System.out.println(out.toString());

    }catch(Exception e) {
      System.out.println("파일 출력 예외 발생 !");
      e.printStackTrace();
    }


  }
}