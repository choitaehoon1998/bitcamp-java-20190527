package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {
MemberList memberList = new MemberList();
 public Input input;
 public MemberHandler(Input input){
   this.input=input;
 }
  public void addMember() {
    Member member = new Member();

    member.setNo(input.getIntValue("번호는"));
    member.setName(input.getStringValue("이름은"));
    member.setEmail(input.getStringValue("이메일은"));
    member.setPassword(input.getStringValue("비밀번호는"));
    member.setPhoto(input.getStringValue("사진은"));
    member.setPhoneNumber(input.getStringValue("전화번호는"));
    member.setRegisterDate(new Date(System.currentTimeMillis()));

    memberList.add(member);
    System.out.println("저장하였습니다.");
  }

  public void listMember() {
    Member []list = memberList.toArray();
    
    for (Member member : list) {
      // 레퍼런스 배열에서 한 개의 인스턴스 주소를 꺼낸다.
      
      // 그 인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n", member.getNo(), member.getName(), 
          member.getEmail(),member.getPassword(), member.getPhoto(),
          member.getPhoneNumber(), member.getRegisterDate());
    }
  }



}
