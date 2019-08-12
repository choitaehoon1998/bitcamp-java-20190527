package com.eomcs.lms.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;

//stateful 통신방식을 stateless 통신방식으로 변경한다
//매번 요청할때마다 서버와 연결한다
//서버의 응답을 받으면 연결을 끊는다 .
public class LessonDaoProxy implements LessonDao {
  String host;
  int port;

  public LessonDaoProxy(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public int insert(Lesson lesson) throws Exception {
    try(Socket socket = new Socket(host,port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());){
      out.writeUTF("/lesson/add");
      out.writeObject(lesson);
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      return 1;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Lesson> findAll() throws Exception {
    try(Socket socket = new Socket(host,port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());){
    out.writeUTF("/lesson/list");
    out.flush();

    if (!in.readUTF().equals("ok"))
      throw new Exception(in.readUTF());

    return (List<Lesson>) in.readObject();
    }
  }

  @Override
  public Lesson findBy(int no) throws Exception {
    try(Socket socket = new Socket(host,port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());){
  out.writeUTF("/lesson/detail");
  out.writeInt(no);
  out.flush();

  if (!in.readUTF().equals("ok"))
    throw new Exception(in.readUTF());

  return (Lesson) in.readObject();
    }
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    try(Socket socket = new Socket(host,port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());){
    out.writeUTF("/lesson/update");
    out.writeObject(lesson);
    out.flush();

    if (!in.readUTF().equals("ok"))
      throw new Exception(in.readUTF());

    System.out.println("처리 완료!");
    return 1;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(Socket socket = new Socket(host,port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());){
    out.writeUTF("/lesson/delete");
    out.writeInt(no);
    out.flush();

    if (!in.readUTF().equals("ok"))
      throw new Exception(in.readUTF());

    return 1;
    }
  }
  public static void main(String[] args)throws Exception {
    LessonDaoProxy daoProxy = new LessonDaoProxy("localhost",8888);
//  Lesson lesson = new Lesson();
//  lesson.setNo(100);
//  lesson.setContents("okokok");
//  
//  daoProxy.insert(lesson);
//  System.out.println("입력성공");

 
// Lesson lesson =daoProxy.findBy(100);
// System.out.println(lesson);
  
  
//  List<Lesson>lessons =daoProxy.findAll();
// for(Lesson lesson : lessons) {
//   System.out.println(lesson);
// }

  
//  Board board = new Board();
//  board.setNo(100);
//  board.setContents("dhghfkrmfjgrnsdy");
  
//  daoProxy.update(board);
//  Board board2 = daoProxy.findBy(100);
//  System.out.println(board2);
 
  
//  daoProxy.delete(100);
//  System.out.println("삭제되었습니다");
  }
}
