// 버퍼 사용 - 사용 전
// 
package ch22.c.ex3;

import java.io.FileInputStream;

public class Test01_1 {
  public static void main(String[] args)  {
    int count =0;
    try {
      FileInputStream in = new FileInputStream("temp/jls12.pdf");
      
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








