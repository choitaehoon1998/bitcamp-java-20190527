// v32_6: 회원 데이터를 다루는 CRUD 명령을 처리한다 . 
package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;

public class ServerApp {
  static ObjectInputStream in;
  static ObjectOutputStream out;
  static ArrayList <Member>memberList = new ArrayList<>();
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
        // 다른 메서드가 사용할수있도록 입출력 스트림을 스태틱 변수에 저장한다
        ServerApp.in =in;
        ServerApp.out=out;
        loop:
        while(true) {
          // 클라이언트가 보낸 명령을 읽는다 
          String command = in.readUTF();
          System.out.println(command + "요청 처리중 ");
          
          // 명령어에 따라 처리한다 . 
          switch(command){
            //클라이언트가 보낸 객체를 읽는다 
            case"/member/add":
             addmember();
             break;
            case"/member/update":
              updatemember0();
              break;
            case"/member/delete":
              deletemember();
              break;
            case"/member/detail":
              detailmember();
              break;
            case"/member/list":
              listmember();
              break;
            case"quit":
              out.writeUTF("ok");
              break loop;
              default:
                out.writeUTF("fail");
                out.writeUTF("지원하지않는 명령입니다");
          }
          out.flush();
          System.out.println("클라이언트에게 응답완료 ");
        }//loop:라벨의 끝 
        out.flush();
      }
      
      System.out.println("클라이언트와 연결을 끊었음 ");
    } catch (Exception e) {
      e.printStackTrace();
    } 
    System.out.println("서버 종료 ");
  }
  
 

  private static void deletemember() throws Exception {
    int no = in.readInt();
    
    int index = indexOfMember(no);
    if(index == -1) {
      fail("해당번호의 회원이없습니다");
      return;
    }
    memberList.remove(index);
    out.writeUTF("ok");
    /*
    for(Member m :memberList) {
      if(m.getNo()==no) {
       System.out.println( memberList.remove(m));
        out.writeUTF("ok");
        return;
      }
    }
   
    out.writeUTF("fail");
    out.writeUTF("해당번호의 회원이없습니다");
     */
  }
  private static void detailmember() throws Exception {
    int no = in.readInt();
    int index = indexOfMember(no);
    if(index == -1) {
      fail("해당번호의 회원이없습니다");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(memberList.get(index));
    
    
    /*
    for(Member m :memberList) {
      if(m.getNo()==no) {
        out.writeUTF("ok");
        out.writeObject(m);
        return;
      }
    }
    out.writeUTF("fail");
    out.writeUTF("해당번호의 회원이없습니다");
    */
  }


  private static void updatemember() throws Exception {
   
    Member member = (Member)in.readObject();
    int no = in.readInt();
    int index = indexOfMember(no);
    if(index == -1) {
      fail("해당번호의 회원이없습니다");
      return;
    }
    memberList.set(index, member);
    out.writeUTF("ok");
    
    
   /*
    for(int i=0; i<memberList.size();i++) {
      Member m = memberList.get(i);
      if(m.getNo()==member.getNo()) {
        memberList.set(i, member);
        out.writeUTF("ok");
        return;
      }
    }
   out.writeUTF("fail");
   out.writeUTF("해당번호의 회원이이없습니다.");
    */
  }
  private static void updatemember0() throws Exception {
    
    Member member = (Member)in.readObject();
    for(Member m :memberList) {
      if(m.getNo()==member.getNo()) {
        m.setName(member.getName());
        m.setEmail(member.getEmail());
        m.setPassword(member.getPassword());
        m.setPhoto(member.getPhoto());
        m.setTel(member.getTel());
        m.setRegisteredDate(member.getRegisteredDate());
        out.writeUTF("ok");
        return;
      }
    }
    fail("해당번호의 회원이없습니다");
    
  }



  private static void addmember()throws Exception {
    Member member = (Member)in.readObject();
    memberList.add(member);
    out.writeUTF("ok");
   
  }
  private static void listmember()throws Exception {
    out.writeUTF("ok");
    out.reset();// 기존에 시리얼 라이즈 했던 객체의 상태를 무시하고 다시 
    // 다시 시리얼 라이즈한다 . 
    out.writeObject(memberList);
  }
  private static int indexOfMember(int no) {
    int i =0;
    for(Member m : memberList) {
      if(m.getNo()== no) {
        return i;
      }
      i++;
    }
    return -1;
  }
  private static void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }
}

