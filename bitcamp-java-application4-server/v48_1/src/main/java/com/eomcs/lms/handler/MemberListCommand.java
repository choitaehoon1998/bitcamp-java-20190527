package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Component;
import com.eomcs.util.RequestMapping;
@Component("/member/list")
public class MemberListCommand {
  private MemberDao memberDao;
  
  @RequestMapping
  public void execute(BufferedReader in , PrintStream out ) {
   try {
     List<Member>members = memberDao.findAll();
     for (Member member :members) { 
       out.printf("%s,%s,%s,%s,%s\n",
           member.getNo(),member.getName(),member.getEmail(),member.getRegisteredDate(),
           member.getTel());
     }
   }catch(Exception e) {
     out.println("데이터 목록 조회에 실패하였습니다.");
     e.printStackTrace();
     
   }

  }
}