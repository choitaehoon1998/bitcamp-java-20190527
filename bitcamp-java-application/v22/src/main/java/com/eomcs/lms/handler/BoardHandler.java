package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;
import com.eomcs.util.LinkedList;

public class BoardHandler {
  private LinkedList<Board> boardList = new LinkedList<>();
  private Input input;

  public BoardHandler(Input input) {
    this.input = input;
  }

  public void listBoard() {
    Board[] boards = new Board[boardList.size()];
    boardList.toArray(boards);
    // Board []boards = boardList.toArray(new Board[] {});
    for (Board board : boards) {
      System.out.printf("%s, %s, %s, %s\n", board.getNo(), board.getContents(),
          board.getCreatedDate(), board.getViewCount());
    }
  }

  public void addBoard() {
    Board board = new Board();

    board.setNo(input.getIntValue("번호? "));
    board.setContents(input.getStringValue("내용? "));
    board.setCreatedDate(new Date(System.currentTimeMillis()));

    boardList.add(board);
    System.out.println("저장하였습니다.");
  }

  public void detailBoard() {
    int no = input.getIntValue("번호? ");
    // 사용자가 입력한 번호를 가지고 목록에서 그번호에 해당하는 Lesson 객체가 들어있는
    // 인덱스를 알아낸다 .
    Board lesson = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        lesson = temp;
        break;
      }
    }
    if (lesson == null) {
      System.out.println("해당 번호의 데이터가 없습니다.");
      return;
    }
    System.out.printf("내용은 %s \n", lesson.getContents());
    System.out.printf("등록일은 %s \n", lesson.getCreatedDate());
    

  }
  public void updateBoard() {
    int no = input.getIntValue("번호? ");
    Board board = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        board = temp;
        break;
      }
    }
    if (board == null) {
      System.out.println("해당 번호의 데이터가 없습니다.");
      return;
    }
    // 사용자로부터 변경할 값을 입력받는다 .
    String str = input.getStringValue("내용(" + board.getContents() + ")?");
    if (str.length() > 0) {
      board.setContents(str);
    }
    board.setCreatedDate(input.getDateValue("시작일(" + board.getCreatedDate() + ")?"));

    System.out.println("데이터을 변경하였습니다.");

  }

  public void deleteBoard() {
    
    int no = input.getIntValue("번호? ");
    Board board = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        boardList.remove(i);
        System.out.println("데이터를 삭제하였습니다.");
        return;
      }
    }
    if (board == null) {
      System.out.println("해당 번호의 데이터가 없습니다.");
      return;
    }
  }
  
}
