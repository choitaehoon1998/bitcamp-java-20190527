package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

// 게시물 요청을 처리하는 담당자
public class BoardServlet implements Servlet {
  // 게시물 DAO 를 교체하기 쉽도록 인터페이스의 레퍼런스로 선언한다 . 
  BoardDao boardDao;
  
  ObjectInputStream in;
  ObjectOutputStream out;

  public BoardServlet(BoardDao boardDao,ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;
    // 서블릿이 사용할 DAO 를 직접만들지않고 외부에서 주입받아 사용한다 . 
    // 이렇게 의존하는 객체를 외부에서 주입받아서 사용하는 방법을 
    // "의존성 주입(Dependency Injection : DI)"라고 부른다 
    // => 그래야만 객체를 주입하기 쉽다 
    this.boardDao = boardDao;
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
