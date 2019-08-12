package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class ServerTest {


  public static void main(String[] args) throws Exception{
    System.out.println("[수업관리시스템 서버 애플리케이션 테스트]");
    try (Socket socket = new Socket("192.168.0.79",8888);
        //서버와의 입출력을 위해 스트림 객체를 준비한다 
        //버퍼를 사용할경우 , 데이터를 보내는쪽에서는 출력스트림을 먼저 준비하라 !
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream())){
      ObjectInputStream in = new ObjectInputStream(
          socket.getInputStream());
      
      System.out.println("서버와 연결되었음 .");

      Member member = new Member();
      member.setNo(1);
      member.setName("최태훈");
      member.setEmail("c@n.com");
      member.setPhoto("사진.gif");
      member.setTel("01033534234");

      out.writeUTF("add");
      out.writeObject(member);
      
      System.out.println("add 요청함 ");

      // 서버가 보낸 데이터를 읽는다
      String response = in.readUTF();
      System.out.println("--->"+response);

      // 서버가 보낸 데이터를 콘솔창에 출력한다

      member = new Member();
      member.setNo(2);
      member.setName("임꺽정");
      member.setEmail("asdfjksadf@n.com");
      member.setPhoto("leem.gif");
      member.setTel("0103-4234");

      out.writeUTF("add");
      out.writeObject(member);
      out.flush();
      System.out.println("add 요청함 ");
      // 서버가 보낸 데이터를 읽는다
      response = in.readUTF();
      System.out.println("--->"+response);

      out.writeUTF("list");
      out.flush();
      System.out.println("list 요청함 ");
      
      // 서버가 보낸 데이터를 읽는다
      response = in.readUTF();
      System.out.println("--->"+response);
      
      @SuppressWarnings("unchecked")
      List<Member> list =(List<Member>)in.readObject();
      System.out.println("----------------");
      for(Member m : list) {
        System.out.println(m);
      }
      System.out.println("---------------");
      //서버가 처리할수없는 명령어 보내기 
      out.writeUTF("delete");
      out.flush();
      System.out.println("delete 요청함 ");
      // 서버가 보낸 데이터를 읽는다
      response = in.readUTF();
      System.out.println("--->"+response);
      
      response = in.readUTF();
      System.out.println("--->"+response);
      
   
      out.writeUTF("quit");
      out.flush();
      System.out.println("quit 요청함 ");
      // 서버가 보낸 데이터를 읽는다
      response = in.readUTF();
      System.out.println("--->"+response);
      
    
    } catch (IOException e) {
      //예외가 발생하면 일단 어디에서 예외가 발생하였는지 확인하기위해 호출 정보를 모두 출력한다 . 
      e.printStackTrace();
    }
    System.out.println("서버와 연결 끊음 ");
  }

}

