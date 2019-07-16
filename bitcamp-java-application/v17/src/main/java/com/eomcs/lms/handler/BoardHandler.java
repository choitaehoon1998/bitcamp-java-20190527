package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Input;

public class BoardHandler {
  private ArrayList boardList = new ArrayList();

  Input input;
  public static Scanner keyScan;

  public BoardHandler(Input input) {
    this.input = input;
  }

  public void addBoard() {
    Board board = new Board();
    board.no = input.getStringValue("번호는");
    board.contents = input.getStringValue("내용은");
    board.createdDate = input.getDateValue("작성일은");
    board.viewCount = input.getIntValue("조회수는");

    boardList.add(board);
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    Object[] list = boardList.toArray();
    for (Object obj : list) {
      Board board = (Board) obj;
      System.out.printf("%s ,%s,%s,%s \n", board.no, board.contents, board.createdDate,
          board.viewCount);
    }
  }


}
