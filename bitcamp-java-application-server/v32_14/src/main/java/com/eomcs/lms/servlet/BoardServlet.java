package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.dao.serial.BoardSerialDao;
import com.eomcs.lms.domain.Board;

// 게시물 요청을 처리하는 담당자
public class BoardServlet implements Servlet {
  
  BoardSerialDao boardDao;
  
  ObjectInputStream in;
  ObjectOutputStream out;

  public BoardServlet(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;

    boardDao = new BoardSerialDao("./board.ser");
  }

  public void saveData() {
    boardDao.saveData();
  }

  @Override
  public void service(String command) throws Exception {
    switch (command) {
      // 클라이언트가 보낸 객체를 읽는다
      case "/board/add":
        addboard();
        break;
      case "/board/update":
        updateboard();
        break;
      case "/board/delete":
        deleteboard();
        break;
      case "/board/detail":
        detailboard();
        break;
      case "/board/list":
        listboard();
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지않는 명령입니다");
    }
  }

  private void addboard() throws Exception {
    Board board = (Board) in.readObject();
    
    // 서버쪽에서 게시글 생성일을 설정해야 한다
    // => 클라이언트에서 보내 온 날짜는 조작된 날짜일 수 있기 때문이다 . 
    board.setCreatedDate(new Date(System.currentTimeMillis()));
    if(boardDao.insert(board)==0) {
      fail("해당 게시물을 입력할수없습니다");
      return;
    }
    out.writeUTF("ok");
    
    
  }

  private void updateboard() throws Exception {
    Board board = (Board) in.readObject();
    // 서버쪽에서 게시글 생성일을 설정해야 한다
    // => 클라이언트에서 보내 온 날짜는 조작된 날짜일 수 있기 때문이다 . 
    board.setCreatedDate(new Date(System.currentTimeMillis()));
    if (boardDao.update(board) == 0) {
      fail("해당번호의 게시물이없습니다");
      return;
    }
    out.writeUTF("ok");
  }

  private void deleteboard() throws Exception {
    int no = in.readInt();
    
    if (boardDao.delete(no) == 0) {
      fail("해당번호의 게시물이없습니다");
      return;
    }
    out.writeUTF("ok");
    
  }

  private void detailboard() throws Exception {
    int no = in.readInt();
    Board board = boardDao.findBy(no);
    if (board == null) {
      fail("해당번호의 게시물이없습니다");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(board);
  }
  
  private void listboard() throws Exception {

    out.writeUTF("ok");
    out.reset();// 기존에 시리얼 라이즈 했던 객체의 상태를 무시하고 다시
    // 다시 시리얼 라이즈한다 .
    out.writeObject(boardDao.findAll());


  }







  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }


}
