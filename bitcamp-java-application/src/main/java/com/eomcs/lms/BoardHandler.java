package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class BoardHandler {
  static Scanner keyScan;
  static Board[] boards = new Board[100];
  static int boardssize = 0;
  
  static void addBoard() {
    Board board = new Board();
    board.no = getStringValue("번호는");
    board.contents = getStringValue("내용은");
    board.createdDate = getDateValue("작성일은");
    board.viewCount = getIntValue("조회수는");

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

  private static int getIntValue(String message) {
    int value = 0;
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  private static String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }

  private static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로 입력하세요.");
      }
    }
  }

}
