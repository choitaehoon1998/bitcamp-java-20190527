package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardListCommand implements Command {

  private BoardDao boardDao;
  private Input input;

  public BoardListCommand(Input input, BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
   try {
     List<Board>boards = boardDao.findAll();
     for (Board board :boards) { 
       System.out.printf("%s,%s,%s,%s\n",
       board.getNo(),board.getContents(),
       board.getCreatedDate(),board.getViewCount());
       }
   }catch(Exception e) {
     e.printStackTrace();
     
   }

  }
}
