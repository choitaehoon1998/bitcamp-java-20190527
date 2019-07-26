package ch22.c.ex1;

import java.io.FileReader;
import java.io.IOException;

// InputStream에 기능을 덧붙이는 플러그인 역할을 수행하는 클래스이다.
// => 이런 클래스를 데코레이터(decorator)라 한다.
// => 데코레이터는 기능을 덧붙이는 대상 클래스와 같은 조상을 가져야 한다.
//    그리고 생성자에게 대상 객체 주소를 받아야 한다.
//    작업을 수행할 때 대상 객체를 사용한다.
//    그리고 자신만의 기능을 덧붙인다.
//상속을이용한 기능추가
// 기존의 fileinpuststream에 버퍼링 기능 추가하기 .
public class BufferedReader extends FileReader {
  
  char[] buf = new char[1024];
  int size = 0;
  int cursor = 0;
  
  public BufferedReader(String name) throws IOException {
    super(name);
  }
  @Override
  public int read() throws IOException {
    if (cursor >= size) {//버퍼에보관된 데이터를 다읽었으면
      size = read(buf);//다시 상속받은 메서드를 사용하여 1024 바이트를 다읽어온다
      
      if (size == -1)//믈론 파일에 읽은 데이터가없다면 즉 다읽었따면  다시커서의 위치를 -1을리턴한다
        return -1;
      cursor = 0;
    }
    return buf[cursor++];//현재 커서가 가르키는 위치의 가리키는 위치의 버퍼항목을 리턴한다 .
  }
  
}






