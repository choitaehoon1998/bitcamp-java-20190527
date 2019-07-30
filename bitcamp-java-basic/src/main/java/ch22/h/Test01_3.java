// 파일 입출력과 예외처리  -try-with-resources
package ch22.h;

import java.io.FileOutputStream;
import java.io.IOException;


public class Test01_3 {
  public static void main(String[] args) {
    //try-with-resource
    // => java.lang.AutoClosable 구현체인경우 
    //    try 블럭을 벗어나기전에 자동으로 close()가 호출되게 하는문법 .
    // => 이문법을 사용하면 개발자가 finally 블록에서 일일이 close()를 호출하는 
    //    번거로움을 피할수있다
    // 문법:
    //    try(AutoClosable 객체준비) {
    //    } catch (...){
    //    } finally{
    //    }
    try(FileOutputStream out = new FileOutputStream("temp3/data.bin")) {

      out.write(0x55);
    } catch (IOException e) {
      System.out.println("파일입출력예외발생");
      e.printStackTrace();
    } 

    System.out.println("출력완료");
  }
}

