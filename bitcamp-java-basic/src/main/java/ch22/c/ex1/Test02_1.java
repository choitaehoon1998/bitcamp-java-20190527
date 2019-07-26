// 버퍼 사용 (for 텍스트파일)- 사용 전
// 
package ch22.c.ex1;

import java.io.FileReader;

public class Test02_1 {
  public static void main(String[] args) {
    int count =0;
    try {
      FileReader in = new FileReader("temp/jls12.txt");
      
      System.out.println("데이터 읽는 중...");
      
      long start = System.currentTimeMillis();
     
      int b;
      while ((b = in.read()) != -1) {
      count++;
      }
      
      long end = System.currentTimeMillis();
      System.out.println(end - start);
      
      in.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("출력 완료!");
  }
}








