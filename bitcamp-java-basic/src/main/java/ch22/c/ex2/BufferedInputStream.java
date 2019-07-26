package ch22.c.ex2;

import java.io.IOException;
import java.io.InputStream;

// InputStream에 기능을 덧붙이는 플러그인 역할을 수행하는 클래스이다.
// => 이런 클래스를 데코레이터(decorator)라 한다.
// => 데코레이터는 기능을 덧붙이는 대상 클래스와 같은 조상을 가져야 한다.
//    그리고 생성자에게 대상 객체 주소를 받아야 한다.
//    작업을 수행할 때 대상 객체를 사용한다.
//    그리고 자신만의 기능을 덧붙인다.
public class BufferedInputStream extends InputStream {
  
  InputStream in;
  byte[] buf = new byte[1024];
  int size = 0;
  int cursor = 0;
  
  public BufferedInputStream(InputStream in) {
    this.in = in;
  }
  
  public int read() throws IOException {
    if (cursor >= size) {//버퍼에보관된 데이터를 다읽었으면
      size = in.read(buf);//다시 fileinputStream을 사용하여 1024를 입ㄹ거옹ㄴ다
      
      if (size == -1)//믈론 파일에 읽은 데이터가없다면 즉 다읽었따면  다시커서의 위치를 0으로 반한횐다 ,
        return -1;
      cursor = 0;
    }
    //바이트 배열에 들어있는 , 바이트의값이 양수일경우 , int로 리턴하면 ㅡ그값 그대로 리턴된다
    //예 0x7f (10진수로 129 )값을 리턴한다면 4바이트 int 0x0000007f 가 리탄된다
    // 문제는 첫비트가 1로 시작하는 바이트값을 인트로 리턴하는경우이다
    // jvm 은 첫비트가 1일때  음수간주하고 
    //바이트값을 인트로 바꿀때앞의 3바이트 를 모두 음수 를 의미하는 1로 설정한다
    //0x80 값을 리턴함다면 4바이트 인트값 4바이트 인트값 0xfffff80이 리턴된다
    //즉 바이트 배열에 ㅔ들어가있는 값의 첫비트가 1로 시작할경우 이런오류가 발생하는겅시다
    // 원래의 바이트값을 온전히 4바이트로 바꾸리턴하고싶다면 
    //앞의 3바이트를 무조건 0으러ㅗ 만들면된다
    //즉 다음과같이 4바이트로 변환된값 을 리턴하기전에
    //비트연산자&를 사용하여 앞의 3바이트를 0으로 설정하라
   //예_ ox80 -> ㅐㅌ ㄹㄹㄹㄹ80 0x 0000
    
    
    // 바이트의 값을 온전히 4바이트 int 값으로 변환하기 위해
    // 0x000000ff 값을 & 비트 연산한다.
    // => 0xff 상수 값은 0x000000ff 를 의미한다.
    return buf[cursor++] & 0xff;//현재 커서가 가르키는 위치의 가리키는 위치의 버퍼항목을 리턴한다 .
  }
  
}






