package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.util.Input;

public class BoardDeleteCommand implements Command {

  private BoardDao  boardDao;

  public BoardDeleteCommand(BoardDao  boardDao) {
    this.boardDao = boardDao;
  }
  public String getCommandName() {
    return "/board/delete";
  }

  @Override
  public void execute(BufferedReader in , PrintStream out ) {

    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Board 객체를 찾는다.
    try{
      int no = Input.getIntValue(in,out,"번호? ");
      if (boardDao.delete(no) > 0) {
        out.println("데이터삭제");
      } else {
        out.println("해당데이터가없습니다");
      }
    } catch (Exception e) {
      out.println("데이터 삭제에 실패했습니다.");
      System.out.println(e.getMessage());
    }

  }

}
