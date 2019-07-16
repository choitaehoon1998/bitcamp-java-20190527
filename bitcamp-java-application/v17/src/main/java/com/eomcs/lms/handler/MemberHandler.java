package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Input;

public class MemberHandler {
  Input input;
  public static Scanner keyScan;
  ArrayList memberList = new ArrayList();

  public MemberHandler(Input i) {
    this.input = i;
  }

  public void addMember() {
    Member member = new Member();

    member.no = input.getIntValue("번호는");
    member.name = input.getStringValue("이름은");
    member.email = input.getStringValue("이메일은");
    member.password = input.getStringValue("비밀번호는");
    member.photo = input.getStringValue("사진은");
    member.phoneNumber = input.getStringValue("전화번호는");
    member.registerDate = new Date(System.currentTimeMillis());

    memberList.add(member);


  }

  public void listMember() {
    Object[] list = memberList.toArray();
    for (Object obj : list) {
      Member members = (Member) obj;
      // 레퍼런스 배열에서 한 개의 인스턴스 주소를 꺼낸다.
      // 그 인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n", members.no, members.name, members.email,
          members.password, members.photo, members.phoneNumber, members.registerDate);
    }
  }



}
