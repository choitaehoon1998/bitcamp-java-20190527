package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardDetailCommand implements Command {

  private BoardDao boardDao;

  public BoardDetailCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  public String getCommandName() {
    return "/board/detail";
  }

  @Override
  public void execute(BufferedReader in , PrintStream out ) {

    try {
      //클라이언트에게 번호를 요구하여 받는다. 
      int no = Input.getIntValue(in, out, "번호?");

      Board board = boardDao.findBy(no);

      if(board ==null) {
        out.println("해당번호의 데이터가없습니다");
        return;
      }
      boardDao.increaseViewCount(no);

      out.printf("내용 :%s\n",board.getContents());
      out.printf("생성일: %s\n",board.getCreatedDate());
      out.printf("조회수 :%s \n",board.getViewCount());
    } catch (Exception e1) {
      out.println("데이터 조회에 실패했습니다.");
      System.out.println(e1.getMessage());
    }


  }


}
