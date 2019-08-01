// v32_4:클라이언트와 서버간에 데이터 주고받기 2- 인스턴스 주고받기  
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.eomcs.lms.domain.Member;

public class ServerApp {

  public static void main(String[] args) {
    System.out.println("[수업관리시스템 서버 애플리케이션]");

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작");

      try(Socket clientSocket =serverSocket.accept();
          
          ObjectInputStream in = new ObjectInputStream(
            clientSocket.getInputStream());
          ObjectOutputStream out = new ObjectOutputStream(
              clientSocket.getOutputStream())){
        System.out.println("클라이언트와 연결되었음");

        // 클라이언트가 보낸 serialize 된 인스턴스 데이터를 읽는다 
        Member member = (Member)in.readObject();
        System.out.println("클라이언트가 보낸 데이터를 읽었음");
        // 클라이언트가 보낸 문자열이 궁금하다 . 서버쪽 콘솔창에 출력해보자 
        System.out.println("--->"+member);

        // 클라이언트에게 데이터를 잘받았다고 응답한다 . 
        out.writeUTF( "OK");
        out.flush();//printerwriter 에 출력한 데이터는 버퍼에있다 버퍼에있는 데이터를 강제로 출력하라 
        System.out.println("클라이언트로 데이터를 보냈음.");

      }
      System.out.println("클라이언트와 연결을 끊었음 ");
    } catch (Exception e) {
      e.printStackTrace();
    } 
    System.out.println("서버 종료 ");
  }
}

