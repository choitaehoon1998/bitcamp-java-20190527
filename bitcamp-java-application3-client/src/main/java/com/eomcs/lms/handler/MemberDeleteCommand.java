package com.eomcs.lms.handler;

import com.eomcs.lms.dao.MemberDao;
import com.eomcs.util.Input;

public class MemberDeleteCommand implements Command {
  private MemberDao memberDao;
  private Input input;

  public MemberDeleteCommand(Input input, MemberDao memberDao) {
    this.input = input;
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");

    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Member 객체를 찾는다.
    try {
      if (memberDao.delete(no) > 0) {
        System.out.println("데이터삭제");
      } else {
        System.out.println("해당데이터가 없습니다");
      }
    } catch (Exception e) {
      System.out.println("데이터 삭제에 실패했습니다.");
      System.out.println(e.getMessage());
    }

  }
}
