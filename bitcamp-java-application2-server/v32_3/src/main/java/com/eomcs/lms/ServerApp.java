// v32_3:클라이언트와 서버간에 데이터 주고받기 -클라이언트가 보낸 데이터를 되돌려 보내기 
package com.eomcs.lms;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
  
  public static void main(String[] args) {
    System.out.println("[수업관리시스템 서버 애플리케이션]");

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작");

      try(Socket clientSocket =serverSocket.accept();
          //클라이언트 연결 객체 (소켓)를 가지고 I/O stream 객체를 얻는다 
          // => 사용하기 편하게 데코레이터를 붙인다 . 
          BufferedReader in = new BufferedReader(
              new InputStreamReader(clientSocket.getInputStream()));
          PrintWriter out = new PrintWriter(
              new BufferedOutputStream(clientSocket.getOutputStream()))){
        System.out.println("클라이언트와 연결되었음");
        
        // 클라이언트가 보낸 데이터를 읽는다 
        //  => 보낸 규칙에 맞춰서 읽어야 한다 .
        String message = in.readLine();
        System.out.println("클라이언트가 보낸 데이터를 읽었음");
        // 클라이언트가 보낸 문자열이 궁금하다 . 서버쪽 콘솔창에 출력해보자 
        System.out.println("--->"+message);
       
        // 클라이언트가 보낸 문자열을 그대로  리턴한다 
        out.println( "[최태훈]" + message);
        out.flush();//printerwriter 에 출력한 데이터는 버퍼에있다 버퍼에있는 데이터를 강제로 출력하라 
        System.out.println("클라이언트로 데이터를 보냈음.");
        
      }
      System.out.println("클라이언트와 연결을 끊었음 ");
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("서버 종료 ");
  }
}

