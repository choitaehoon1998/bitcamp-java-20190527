package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;
@Component
public class MemberCommand  {
  private MemberDao memberDao;

  public MemberCommand( MemberDao memberDao) {
    this.memberDao = memberDao;
  }


  @RequestMapping("/member/add")
  public void add(BufferedReader in , PrintStream out ) {

    try {
      Member member = new Member();
      member.setName(Input.getStringValue(in,out,"이름? "));
      member.setEmail(Input.getStringValue(in,out,"이메일? "));
      member.setPassword(Input.getStringValue(in,out,"암호? "));
      member.setPhoto(Input.getStringValue(in,out,"사진? "));
      member.setTel(Input.getStringValue(in,out,"전화? "));
      memberDao.insert(member);
      out.println("저장하였습니다.");
    } catch (Exception e) {
      out.println("데이터저장에 실패하였습니다");
      System.out.println(e.getMessage());
    }
  }
  @RequestMapping("/member/delete")
  public void delete(BufferedReader in , PrintStream out ) {

    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Member 객체를 찾는다.
    try {
      int no = Input.getIntValue(in,out,"번호? ");
      if (memberDao.delete(no) > 0) {
        out.println("데이터삭제");
      } else {
        out.println("해당데이터가 없습니다");
      }
    } catch (Exception e) {
      out.println("데이터 삭제에 실패했습니다.");
      System.out.println(e.getMessage());
    }

  }
  @RequestMapping("/member/detail")
  public void detail(BufferedReader in , PrintStream out ) {
    try {
      int no = Input.getIntValue(in,out,"번호? ");
      Member member = memberDao.findBy(no);
      if(member ==null) {
        out.println("해당번호의 데이터가없습니다");
        return;
      }
      out.printf("번호 :%s\n",member.getNo());
      out.printf("이름: %s\n",member.getName());
      out.printf("이메일 :%s\n",member.getEmail());
      out.printf("비밀번호: %s\n",member.getPassword());
      out.printf("사진 :%s\n",member.getPhoto());
      out.printf("등록일: %s\n",member.getRegisteredDate());
      out.printf("전화번호: %s\n",member.getTel());

    } catch (Exception e1) {
      out.println("데이터조회에실패하였습니다");
      e1.printStackTrace();
    }


  }
  
  @RequestMapping("/member/list")
  public void list(BufferedReader in , PrintStream out ) {
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

  @RequestMapping("/member/search")
  public void search(BufferedReader in , PrintStream out ) {
   try {
     String keyword = Input.getStringValue(in, out, "검색어?");
     
     List<Member>members = memberDao.findByKeyword(keyword);
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
  @RequestMapping("/member/update")
  public void update(BufferedReader in , PrintStream out ) {
    
    try {
      int no = Input.getIntValue(in,out,"번호? ");
      Member member = memberDao.findBy(no);
      if (member == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
    // 사용자로부터 변경할 값을 입력 받는다.
      Member data = new Member();
      data.setNo(no);
    String str = Input.getStringValue(in,out,"이름(" + member.getName() + ")? ");
    if (str.length() > 0) {
      data.setName(str);
    }
    
    str = Input.getStringValue(in,out,"이메일(" + member.getEmail() + ")? ");
    if (str.length() > 0) {
      data.setEmail( str);
    }
    
    str = Input.getStringValue(in,out,"암호? ");
    if (str.length() > 0) {
      data.setPassword( str);
    }
    
    str = Input.getStringValue(in,out,"사진(" + member.getPhoto() + ")? ");
    if (str.length() > 0) {
      data.setPhoto(str);;
    }
    
    str = Input.getStringValue(in,out,"전화(" + member.getTel() + ")? ");
    if (str.length() > 0) {
      data.setTel(str);
    }
      memberDao.update(data);
      out.println("데이터를 변경하였습니다.");
    
    } catch (Exception e) {
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}