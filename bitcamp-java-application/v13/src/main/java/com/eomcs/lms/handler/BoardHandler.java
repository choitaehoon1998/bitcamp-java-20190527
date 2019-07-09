package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {
  private Board[] boards = new Board[100];
  private int boardssize = 0;

  public static Scanner keyScan;

  public void addBoard() {
    Board board = new Board();
    board.no = Input.getStringValue("번호는");
    board.contents = Input.getStringValue("내용은");
    board.createdDate = Input.getDateValue("작성일은");
    board.viewCount = Input.getIntValue("조회수는");

    boards[boardssize++] = board;
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    for (int i = 0; i < boardssize; i++) {
      Board board = new Board();
      board = boards[i];

      System.out.printf("%s ,%s,%s,%s \n", board.no, board.contents, board.createdDate,
          board.viewCount);
    }
  }


}
