// DataOutputStream + 버퍼기능 = DataOutputStream2 
package ch22.c.ex3.byte_Stream;

public class Test02_1 {
  public static void main(String[] args) throws Exception {

    BufferedDataOutputStream out = new BufferedDataOutputStream("temp/data.bin");

    short s = 0x1122;
    int i = 0x33445566;
    long l = 0x1122334455667788L;
    String str = "ABC가각간";
    boolean b = true;
    System.out.println("출력시작...");
    long start = System.currentTimeMillis();
    for (int cnt = 0; cnt < 100000; cnt++) {

      out.writeShort(s);
      out.writeInt(i);
      out.writeLong(l);
      out.writeUTF(str);
      out.writeBoolean(b);
    }
    
    //버퍼기능을 사용할때는 항상 맨마지막에 잔여 데이터를 출력해야한다 . 
    out.flush();
    long end = System.currentTimeMillis();
    System.out.println(end-start);
    System.out.println("출력 완료!");
    out.close();
  }
}

