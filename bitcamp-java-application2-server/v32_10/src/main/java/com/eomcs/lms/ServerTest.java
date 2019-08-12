package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class ServerTest {
  static ObjectOutputStream out ;
  static  ObjectInputStream in ;
  public static void main(String[] args) throws Exception{
    System.out.println("[수업관리시스템 서버 애플리케이션 테스트]");
    try (Socket socket = new Socket("localhost",8888);
        //서버와의 입출력을 위해 스트림 객체를 준비한다 
        //버퍼를 사용할경우 , 데이터를 보내는쪽에서는 출력스트림을 먼저 준비하라 !
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream())){
      ObjectInputStream in = new ObjectInputStream(
          socket.getInputStream());

      System.out.println("서버와 연결되었음 .");
      ServerTest.in =in;
      ServerTest.out =out;

      // 다른메서드가 입출력 객체를 사용할수있도록 스태틱 변수에 저장한다 

      Member member = new Member();
      member.setNo(1);
      member.setName("최태훈");
      member.setEmail("c@n.com");
      member.setPhoto("사진.gif");
      member.setTel("01033534234");

      System.out.println("---------------");
      if(!add(member)) {
        error();
      }

      // 서버가 보낸 데이터를 콘솔창에 출력한다
      System.out.println("---------------");

      member = new Member();
      member.setNo(2);
      member.setName("임꺽정");
      member.setEmail("asdfjksadf@n.com");
      member.setPhoto("leem.gif");
      member.setTel("0103-4234");
      if(!add(member)) {
        error();
      }
      System.out.println("---------------");
      if(!list()) {
        error();
      }
      System.out.println("---------------");
      //서버가 처리할수없는 명령어 보내기 
      if(!delete()) {
        error();
      }
      System.out.println("---------------");
      if(!list()) {
        error();
      }
      System.out.println("---------------");

      if(!detail()) {
        error();
      }
      System.out.println("---------------");

      if(!list()) {
        error();
      }
      System.out.println("---------------");
      member = new Member();
      member.setNo(1);
      member.setName("홍길동2");
      member.setEmail("asdfjksaasdff@n.com");
      member.setPhoto("leem.afsdgif");
      member.setTel("0103-4123234");
      if(!update(member)) {
        error();
      }
      System.out.println("---------------");

      if(!list()) {
        error();
      }


      System.out.println("---------------");
      if(!quit()) {
        error();
      }
      System.out.println("---------------");

    }  catch (IOException e) {
      //예외가 발생하면 일단 어디에서 예외가 발생하였는지 확인하기위해 호출 정보를 모두 출력한다 . 
      e.printStackTrace();
    }
    System.out.println("서버와 연결 끊음 ");
  }
  private static void error() throws Exception {
    System.out.printf("오류 : %s\n", in.readUTF());

  }
  private static boolean quit()throws Exception{
    out.writeUTF("quit");
    out.flush();
    System.out.print("quit 요청함 ===>");
    // 서버가 보낸 데이터를 읽는다
    if(!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료");
    return true;
  }

  private static boolean detail() throws Exception{
    out.writeUTF("/member/detail");
    out.writeInt(1);
    out.flush();
    System.out.print("detail 요청함 ===>");

    if(!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료");

    System.out.println(in.readObject());
    return true;

  }
  private static boolean delete()throws Exception {    
    out.writeUTF("/member/delete");
    out.writeInt(2);
    out.flush();
    System.out.print("delete 요청함 ===>");

    if(!in.readUTF().equals("ok"))
      return false;
    System.out.println("처리 완료");
    return true;

  }
  private static boolean add (Member m)throws  Exception{
    out.writeUTF("/member/add");
    out.writeObject(m);
    out.flush();
    System.out.print("add 요청함 ===>");
    if(!in.readUTF().equals("ok"))
      return false;
    System.out.println("처리 완료");
    return true;
  }
  private static boolean update(Member m)throws Exception{
    out.writeUTF("/member/update");
    out.writeObject(m);
    out.flush();
    System.out.print("update  요청함 ===>");

    if(!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료");
    return true; 
  }

  private static boolean list ()throws  Exception{
    out.writeUTF("/member/list");
    out.flush();
    System.out.print("list 요청함 ===>");

    if(!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료");


    @SuppressWarnings("unchecked")
    List<Member> list =(List<Member>)in.readObject();
    for(Member m : list) {
      System.out.println(m);
    }
    return true;
  }
}

