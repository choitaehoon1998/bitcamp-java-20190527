package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.domain.Board;

// 게시물 요청을 처리하는 담당자
public class BoardServlet implements Servlet {
  ArrayList<Board> boardList = new ArrayList<>();
  ObjectInputStream in;
  ObjectOutputStream out;

  public BoardServlet(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void service(String command) throws Exception {
    switch(command){
      //클라이언트가 보낸 객체를 읽는다
      case"/board/add":
        addboard();
        break;
      case"/board/update":
        updateboard();
        break;
      case"/board/delete":
        deleteboard();
        break;
      case"/board/detail":
        detailboard();
        break;
      case"/board/list":
        listboard();
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지않는 명령입니다");
    }
  }

  private void updateboard() throws Exception {
    Board board = (Board) in.readObject();
    for (Board m : boardList) {
      if (m.getNo() == board.getNo()) {
        m.setContents(board.getContents());
        m.setCreatedDate(board.getCreatedDate());
        m.setViewCount(board.getViewCount());
        out.writeUTF("ok");
        return;
      }
    }
    fail("해당번호의 회원이없습니다");


  }

  private void listboard() throws Exception {

    out.writeUTF("ok");
    out.reset();// 기존에 시리얼 라이즈 했던 객체의 상태를 무시하고 다시
    // 다시 시리얼 라이즈한다 .
    out.writeObject(boardList);


  }



  private void detailboard() throws Exception {
    int no = in.readInt();
    int index = indexOfBoard(no);
    if (index == -1) {
      fail("해당번호의 회원이없습니다");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(boardList.get(index));


  }



  private void deleteboard() throws Exception {
    int no = in.readInt();

    int index = indexOfBoard(no);
    if (index == -1) {
      fail("해당번호의 회원이없습니다");
      return;
    }
    boardList.remove(index);
    out.writeUTF("ok");

  }

  private void addboard() throws Exception {
    Board board = (Board) in.readObject();
    boardList.add(board);
    out.writeUTF("ok");


  }

  private int indexOfBoard(int no) {
    int i = 0;
    for (Board b : boardList) {
      if (b.getNo() == no) {
        return i;
      }
      i++;
    }
    return -1;
  }

  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }


}
