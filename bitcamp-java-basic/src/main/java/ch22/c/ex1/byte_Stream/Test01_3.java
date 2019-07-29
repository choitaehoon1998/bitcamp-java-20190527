// 버퍼 사용 - 버퍼를 적용하여 데이터 읽기를 대신 처리해주는 도우미 클래스 사용
//
package ch22.c.ex1.byte_Stream;

public class Test01_3 {
  public static void main(String[] args) throws Exception {

    BufferedInputStream in = new BufferedInputStream("temp/jls12.pdf");

    System.out.println("데이터 읽는 중...");

    long start = System.currentTimeMillis();

    int b;
    int count = 0;
    while ((b = in.read()) != -1) {
      count++;
      // 겉으로는 1바이트씩 읽는거같다
      // 그래서 1바이트를 암호화 시키는 코드도 여기에 바로 작성하면된다 .
      // test01_2 .java에 비교하면 훨씬 편하다 .
      // 편한동시에 읽기속도도 더빠르다
      // 어떻게
      // -> BufferedInputStream 의 read()메서드는 FileInputStream 에서 상속받은
      // 메서드를 이용하여 먼저 바이트 배열단위로 데이터를 가져온다음에
      // 그배열에서 1바이트를 리턴한다
      // -> 그이후에는 바이트 배열의 데이터가 떨어질떄까지 게속 바이트 배열에서 값을 꺼내 리턴해준다
      // -> 바이트 배열의 값이 떨어지면 다시 바이트 배열 단위로 데이터를 읽어온다
      // 그래서 1바이트씩 읽더라도 그렇게 속도가 떨어지지않는것이다 .
      // 1바이트를 읽기 전에 먼저 바이트 배열 단위로
    }

    long end = System.currentTimeMillis();
    System.out.println(end - start);
    System.out.println(count);

    in.close();


    System.out.println("출력 완료!");
  }
}


