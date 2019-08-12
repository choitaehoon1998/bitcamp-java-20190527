package com.eomcs.lms.handler;

import com.eomcs.lms.dao.BoardDao;
import com.eomcs.util.Input;

public class BoardDeleteCommand implements Command {

  private BoardDao boardDao;
  private Input input;

  public BoardDeleteCommand(Input input, BoardDao boardDao) {
    this.input = input;
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");

    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Board 객체를 찾는다.
    try {
      boardDao.delete(no);
      System.out.println("데이터삭제");
    } catch (Exception e) {
      System.out.println("데이터 삭제에 실패했습니다.");
      System.out.println(e.getMessage());
    }

  }

}
