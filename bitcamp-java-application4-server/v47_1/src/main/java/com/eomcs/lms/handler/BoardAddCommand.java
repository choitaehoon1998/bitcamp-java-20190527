package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Component;
import com.eomcs.util.Input;
@Component("/board/add")
public class BoardAddCommand implements Command {

  private BoardDao  boardDao;


  public String getCommandName() {
    return "/board/add";
  }

  
  @Override
  public void execute(BufferedReader in , PrintStream out ) {
    
    try {
      Board board = new Board();
      board.setContents(Input.getStringValue(in,out,"내용? "));

      boardDao.insert(board);
      out.println("저장하였습니다.");
    } catch (Exception e) {
      out.println("데이터저장에 실패하였습니다");
      System.out.println(e.getMessage());
    }
  }

}
