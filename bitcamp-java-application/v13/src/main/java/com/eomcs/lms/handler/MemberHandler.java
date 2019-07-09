package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {

 public static Scanner keyScan;
 private Member[] members = new Member[100];
 private int memberssize = 0;

  public int addMember() {
    Member member = new Member();

    member.no = Input.getIntValue("번호는");
    member.name = Input.getStringValue("이름은");
    member.email = Input.getStringValue("이메일은");
    member.password = Input.getStringValue("비밀번호는");
    member.photo = Input.getStringValue("사진은");
    member.phoneNumber = Input.getStringValue("전화번호는");
    member.registerDate = new Date(System.currentTimeMillis());

    members[memberssize++] = member;
    System.out.println("저장하였습니다.");
    return memberssize;
  }

  public void listMember() {
    for (int i = 0; i < memberssize; i++) {
      // 레퍼런스 배열에서 한 개의 인스턴스 주소를 꺼낸다.
      Member member = members[i];
      // 그 인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n", member.no, member.name, member.email,
          member.password, member.photo, member.phoneNumber, member.registerDate);
    }
  }



}
