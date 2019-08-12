package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.dao.serial.MemberSerialDao;
import com.eomcs.lms.domain.Member;

// 게시물 요청을 처리하는 담당자
public class MemberServlet implements Servlet {
  MemberSerialDao memberDao;
  ObjectInputStream in;
  ObjectOutputStream out;

  public MemberServlet(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;

    memberDao = new MemberSerialDao("./member.ser");
  }

  public void saveData() {
    memberDao.saveData();
  }

  @Override
  public void service(String command) throws Exception {
    switch (command) {
      // 클라이언트가 보낸 객체를 읽는다
      case "/member/add":
        addmember();
        break;
      case "/member/update":
        updatemember();
        break;
      case "/member/delete":
        deletemember();
        break;
      case "/member/detail":
        detailmember();
        break;
      case "/member/list":
        listmember();
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지않는 명령입니다");
    }
  }

  private void updatemember() throws Exception {
    Member member = (Member) in.readObject();
    member.setRegisteredDate(new Date(System.currentTimeMillis()));
    if (memberDao.update(member) == 0) {
      fail("해당번호의 멤버이없습니다");
      return;
    }
    out.writeUTF("ok");
  }

  private void detailmember() throws Exception {
    int no = in.readInt();
    Member member = memberDao.findBy(no);
    if (member == null) {
      fail("해당번호의 회원이없습니다");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(member);
  }

  private void deletemember() throws Exception {
    int no = in.readInt();

    if (memberDao.delete(no) == 0) {
      fail("해당번호의 회원이없습니다");
      return;
    }
    out.writeUTF("ok");

  }

  private void addmember() throws Exception {
    Member member = (Member) in.readObject();
    member.setRegisteredDate(new Date(System.currentTimeMillis()));
    if (memberDao.insert(member) == 0) {
      fail("해당 회원을 입력할수없습니다");
      return;
    }
    out.writeUTF("ok");


  }

  private void listmember() throws Exception {

    out.writeUTF("ok");
    out.reset();// 기존에 시리얼 라이즈 했던 객체의 상태를 무시하고 다시
    // 다시 시리얼 라이즈한다 .
    out.writeObject(memberDao.findAll());


  }



  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }


}
