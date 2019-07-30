// 파일 입출력객체의 데코레이터 연결하기 . -try-with-resource
package ch22.h;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Test02_3 {
  public static void main(String[] args) {
    try (DataOutputStream out = new DataOutputStream(
        new BufferedOutputStream(
            new FileOutputStream("temp3/data.bin")))){
      out.writeInt(0x11223344);

    } catch (IOException e) {
      System.out.println("파일입출력예외발생");
      e.printStackTrace();
    } finally {
      //try-with-resource 문법은 자동으로 close()가 호출된다 . 
      //따라서 finally 블럭에서 close()를 호출할 필요가없다 
      // close() 가 아니라 그냥 다른 마무리를 할일이있다면 . 
      // 이블럭에 작성하라 
    }




    System.out.println("출력완료");
  }
}

