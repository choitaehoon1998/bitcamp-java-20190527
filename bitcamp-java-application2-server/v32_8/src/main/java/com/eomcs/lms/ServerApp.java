// v32_8 :회원/수업/게시물요청을 처리하는코드를 별도의 클래스로 분리한다 .
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
  static ObjectInputStream in;
  static ObjectOutputStream out;

  public static void main(String[] args) {
    System.out.println("[수업관리시스템 서버 애플리케이션]");


    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작");

      try (Socket clientSocket = serverSocket.accept();

          ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
          ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
        System.out.println("클라이언트와 연결되었음");
        // 다른 메서드가 사용할수있도록 입출력 스트림을 스태틱 변수에 저장한다
        ServerApp.in = in;
        ServerApp.out = out;
        BoardServlet boardServlet = new BoardServlet(in, out);
        LessonServlet lessonServlet = new LessonServlet(in, out);
        MemberServlet memberServlet = new MemberServlet(in, out);
        loop: while (true) {
          // 클라이언트가 보낸 명령을 읽는다
          String command = in.readUTF();
          System.out.println(command + "요청 처리중 ");

          if (command.startsWith("/board/")) {
            boardServlet.service(command);
            out.flush();
            continue;
          }
          else if (command.startsWith("/lesson/")) {
            lessonServlet.service(command);
            out.flush();
            continue;
          }
          else if (command.startsWith("/member/")) {
            memberServlet.service(command);
            out.flush();
            continue;
          }
          else if (command.equals("quit")) {
            out.writeUTF("ok");
            break loop;
          } else {

            out.writeUTF("fail");
            out.writeUTF("지원하지않는 명령입니다");

          }
        }
      }

    } catch (

    Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버 종료 ");
  }



}


