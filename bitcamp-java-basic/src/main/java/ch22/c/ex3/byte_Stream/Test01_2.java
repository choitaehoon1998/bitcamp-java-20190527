// 버퍼없이 대량의 primitive 타입의 값을 읽기 
package ch22.c.ex3.byte_Stream;

import ch22.c.ex2.byte_Stream.DataInputStream;

public class Test01_2 {
  public static void main(String[] args)throws Exception {
    
        DataInputStream in = new DataInputStream("temp/data.bin");
      
        System.out.println("읽기시작...");
        long start = System.currentTimeMillis();
      // 바이너리 데이터를 읽을 때는 저장한 순서(파일 포맷)에 맞춰 읽어야 한다.
        for(int cnt =0; cnt<100000; cnt++) {
        short s= in.readShort();
        int i =in.readInt();
        long l = in.readLong();
        String str =in.readUTF();
        boolean b = in.readBoolean();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println("읽기 완료!");
      in.close();
  }
}








