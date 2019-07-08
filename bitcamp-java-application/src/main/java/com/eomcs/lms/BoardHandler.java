package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class BoardHandler {
  static Scanner keyScan;
  static Board[] boards = new Board[100];
  static int boardssize = 0;
  
  static void addBoard() {
    Board board = new Board();
    board.no = Input.getStringValue("번호는");
    board.contents = Input.getStringValue("내용은");
    board.createdDate = Input.getDateValue("작성일은");
    board.viewCount = Input.getIntValue("조회수는");

    boards[boardssize++] = board;
    System.out.println("저장하였습니다.");
  }

  static void listBoard() {
    for (int i = 0; i < boardssize; i++) {
      Board board = new Board();
      board = boards[boardssize];

      System.out.printf("%s ,%s,%s,%s \n", board.no, board.contents, board.createdDate,
          board.viewCount);
    }
  }


}
