package com.eomcs.lms;

import java.io.IOException;
import java.net.Socket;

public class ServerTest {
  public static void main(String[] args) {
    System.out.println("[수업관리시스템 서버 애플리케이션 테스트]");

    // 서버에 통신을 연결한다 . 
    // => new Socket(인터넷 주소,포트 번호)
    // => 서버와 연결될때까지 리턴하지않는다 . 
    // 인터넷 주소?
    // => Ip 주소(예:192.168.0.1)
    // => 도메인 이름(예: www.okok.com)
    // 
    // localhost?
    // => 로컬 pc를 가르키는 특수 도메인 이름이다. 
    //
    // 127.0.0.17 
    // 로컬 pc 를 가르키는 특수 ip 주소이다 
    // 
    
    try (Socket socket = new Socket("loaclhost",8888)) {
      System.out.println("서버와 연결되었음 .");


    } catch (IOException e) {
      //예외가 발생하면 일단 어디에서 예외가 발생하였는지 확인하기위해 호출 정보를 모두 출력한다 . 
      e.printStackTrace();
    }
    System.out.println("서버와 연결 끊음 ");

  }
  
}

