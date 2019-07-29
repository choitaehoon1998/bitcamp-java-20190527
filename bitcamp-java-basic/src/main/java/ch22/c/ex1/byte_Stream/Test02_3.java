// 데이터 출력에서 버퍼사용 - 버퍼기능을 대신 수행해주는 BufferedOutputStream 사용 .
package ch22.c.ex1.byte_Stream;

import java.io.FileOutputStream;

public class Test02_3 {
  public static void main(String[] args) throws Exception {

    FileOutputStream out = null;

    out = new FileOutputStream("temp/data.bin");

    // BufferedOutputStream 은 내부적으로 1바이트 파일로 바로 출력하지않고
    // 일단 byte[]에 저장한다 .
    // 바이트배열이 꽉 찼을때 파일로 출력할것이다
    // 그래서test01_1.java에서와 같이 1바이트 씩 출력하느것은 같지만
    // 속도는 이클래스가 더빠르다


    long start = System.currentTimeMillis();

    for (int i = 0; i < 1000000; i++) {
      out.write(0x55);
    }
    // bufferedoutputstream은 바이트 배열이 꽉 찼을때 만 파일로 출력하기때문에
    // 바이트 배열에 데이터가 남아있을경우 강제적으로출력해야한다
    out.flush(); // 버퍼에 남아 있는 것을 방출한다.
    System.out.println("데이터 쓰는 중...");
    long end = System.currentTimeMillis();

    System.out.println(end - start);



    out.close();

    System.out.println("출력 완료!");
  }
}


