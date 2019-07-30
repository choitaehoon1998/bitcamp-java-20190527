// 바이트 배열 객체에 출력하기 . - Stream API 를 사용하여 데이터 출력하기.
package ch22.i.byte_stream;

import java.io.FileOutputStream;

public class Test01_2 {
  public static void main(String[] args) throws Exception {
    // 1에서 100까지의 숫자중 짝수만파일에출력해보자 
    // 1) 값을 출력할 출력객체를 준비한다
    try(FileOutputStream out = new FileOutputStream("temp/data.bin")){


      // 2) 짝수를 파일에 출력한다
      for(int i=0; i<=100; i++) {
        if(i%2==0)
          out.write(i);
      }
    }catch(Exception e) {
      System.out.println("파일 출력 예외 발생 !");
      e.printStackTrace();
    }

  }
}




