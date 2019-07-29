package ch22.c.ex4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

// 데코레이터 설계패턴을 적용하여 기존 아웃풋 스트림에 기능을 추가한다
// 데코레이터 아웃풋 스트림을 상속받는다
public class DataOutputStream extends DecoratorOutputStream {

  public DataOutputStream(OutputStream other) throws FileNotFoundException {
    super(other);
  }

  //자신에게 1바이트 출력하라고 요청이 들어오면 
  //생성자에서 바은 아우풋 스트림을 이용하여
  // 라이트 메서드 생성 
  @Override
  public void write(int b) throws IOException {
    other.write(b);
  }

  public void writeInt(int value) throws IOException {
    write(value >> 24);
    write(value >> 16);
    write(value >> 8);
    write(value);

  }

  public void writeShort(int value) throws IOException {
    write(value >> 8);
    write(value);
  }

  public void writeLong(long value) throws IOException {
    write((int) (value >> 56));
    write((int) (value >> 48));
    write((int) (value >> 40));
    write((int) (value >> 32));
    write((int) (value >> 24));
    write((int) (value >> 16));
    write((int) (value >> 8));
    write((int) (value));
  }

  public void writeUTF(String str) throws IOException {
    // String 인스턴스에 보관된 문자열을 바이트배열로 꺼낸다
    byte[] bytes = str.getBytes("UTF-8");
    // 먼저 출력할 바이트의 개수를 쓴다
    // 문자열을 출력할때 한번에 32767 바이트만 출력할수있도록한다 .
    writeShort(bytes.length);
    // 그리고 문자열의 UTF-8 코드값이 들어있는 바이트 배열을 출력한다
    write(bytes);

  }

  public void writeBoolean(boolean value) throws IOException {
    if (value == true)
      write(1);
    else
      write(0);



  }



}
