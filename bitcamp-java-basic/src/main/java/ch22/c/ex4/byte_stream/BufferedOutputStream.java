package ch22.c.ex4.byte_stream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class BufferedOutputStream extends DecoratorOutputStream {

  public BufferedOutputStream(OutputStream other) throws FileNotFoundException {
    super(other);
    // TODO Auto-generated constructor stub
  }

  byte[] buf = new byte[1024];
  int size = 0;

  @Override
  public void write(int b) throws IOException {
    buf[size++] = (byte) b;
    if (size >= buf.length) {// 배열이 꽉찼으면 파일로 출력한다
      other.write(buf);// 실제출력할때 생성자에서 받은 OutputSTREAM 객체에게 맡겨야한다 .
      size = 0;
    }
  }

  @Override
  public void flush() throws IOException {
    if (size > 0) {// 배열에 출력할 데이터가 남아있다면 파일로 출력한다 .
      other.write(buf, 0, size);// 실제출력할때 생성자에서 받은 OutputSTREAM 객체에게 맡겨야한다 .
    }
    other.flush(); // 실제출력할때 생성자에서 받은 OutputSTREAM 객체에게 전달해야한다 .
  }
}