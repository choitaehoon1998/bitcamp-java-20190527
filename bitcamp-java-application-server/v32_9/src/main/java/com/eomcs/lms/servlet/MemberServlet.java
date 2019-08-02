package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.domain.Member;

// 게시물 요청을 처리하는 담당자
public class MemberServlet implements Servlet {
  ArrayList<Member> memberList = new ArrayList<>();
  ObjectInputStream in;
  ObjectOutputStream out;

  public MemberServlet(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void service(String command) throws Exception {
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
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지않는 명령입니다");
    }
  }

  private void deletemember() throws Exception {
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
  private void detailmember() throws Exception {
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

  /*
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

  private void updatemember0() throws Exception {

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



  private void addmember()throws Exception {
    Member member = (Member)in.readObject();
    memberList.add(member);
    out.writeUTF("ok");

  }
  private void listmember()throws Exception {
    out.writeUTF("ok");
    out.reset();// 기존에 시리얼 라이즈 했던 객체의 상태를 무시하고 다시 
    // 다시 시리얼 라이즈한다 . 
    out.writeObject(memberList);
  }
  private int indexOfMember(int no) {
    int i =0;
    for(Member m : memberList) {
      if(m.getNo()== no) {
        return i;
      }
      i++;
    }
    return -1;
  }

  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }


}
