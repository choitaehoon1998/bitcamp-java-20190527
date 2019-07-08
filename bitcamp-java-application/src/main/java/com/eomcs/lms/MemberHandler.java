package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class MemberHandler {

  static Scanner keyScan;
  static Member[] members = new Member[100];
  static int memberssize = 0;
  static int addMember() {
    Member member = new Member();

    member.no = getIntValue("번호는");
    member.name = getStringValue("이름은");
    member.email = getStringValue("이메일은");
    member.password = getStringValue("비밀번호는");
    member.photo = getStringValue("사진은");
    member.phoneNumber = getStringValue("전화번호는");
    member.registerDate = new Date(System.currentTimeMillis());

    members[memberssize++] = member;
    System.out.println("저장하였습니다.");
    return memberssize;
  }

  static void listMember() {
    for (int i = 0; i < memberssize; i++) {
      // 레퍼런스 배열에서 한 개의 인스턴스 주소를 꺼낸다.
      Member member = members[i];
      // 그 인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n", member.no, member.name, member.email,
          member.password, member.photo, member.phoneNumber, member.registerDate);
    }
  }
  
  
  
  private static int getIntValue(String message) {
    int value = 0;
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  private static String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }

  private static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로 입력하세요.");
      }
    }
  }

}
