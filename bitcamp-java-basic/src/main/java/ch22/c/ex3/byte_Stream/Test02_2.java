// DataInputStream에 버퍼기능을 추가하여 DataInputStream2 만들기
package ch22.c.ex3.byte_Stream;

public class Test02_2 {
  public static void main(String[] args)throws Exception {
    
        BufferedDataInputStream in = new BufferedDataInputStream("temp/data.bin");
      
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








