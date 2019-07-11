package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {
  private Board[] boards = new Board[100];
  private int boardssize = 0;

  public Input input;

  // BoardHandler가 사용하는 Input 객체를 반드시 설정하도록 강제해보자
  // Input 객체처럼 어떤 작업을 실행하기위해 반드시있어야하는 객체를
  // "의존객체(dependency)" 라 부른다 .
  // 의존 객체를 강제로 설정하게 만드는 방법
  // 다음과같이 의존 객체를 넘겨 받는 생성자를 정의하는것이다
  public BoardHandler(Input input) {
    this.input = input;
  }

  public void addBoard() {
    Board board = new Board();
    board.no = input.getStringValue("번호는");
    board.contents = input.getStringValue("내용은");
    board.createdDate = input.getDateValue("작성일은");
    board.viewCount = input.getIntValue("조회수는");

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
