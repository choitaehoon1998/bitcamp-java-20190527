package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.dao.LessonSerialDao;
import com.eomcs.lms.domain.Lesson;

// 게시물 요청을 처리하는 담당자
public class LessonServlet implements Servlet {
  LessonSerialDao lessonDao;
  ObjectInputStream in;
  ObjectOutputStream out;

  public LessonServlet(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;

    lessonDao = new LessonSerialDao("./lesson.ser");
  }


  public void saveData() {
    lessonDao.saveData();
  }

  @Override
  public void service(String command) throws Exception {
    switch (command) {
      // 클라이언트가 보낸 객체를 읽는다
      case "/lesson/add":
        addlesson();
        break;
      case "/lesson/update":
        updatelesson();
        break;
      case "/lesson/delete":
        deletelesson();
        break;
      case "/lesson/detail":
        detaillesson();
        break;
      case "/lesson/list":
        listlesson();
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지않는 명령입니다");
    }
  }

  private void updatelesson() throws Exception {
    Lesson lesson = (Lesson) in.readObject();
    if (lessonDao.update(lesson) == 0) {
      fail("해당번호의 수업이없습니다");
      return;
    }
    out.writeUTF("ok");
  }

  private void detaillesson() throws Exception {
    int no = in.readInt();
    Lesson lesson = lessonDao.findBy(no);
    if (lesson == null) {
      fail("해당번호의 수업이없습니다");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(lesson);
  }

  private void deletelesson() throws Exception {
    int no = in.readInt();

    if (lessonDao.delete(no) == 0) {
      fail("해당번호의 수업이없습니다");
      return;
    }
    out.writeUTF("ok");

  }

  private void addlesson() throws Exception {
    Lesson lesson = (Lesson) in.readObject();
    if (lessonDao.insert(lesson) == 0) {
      fail("해당 수업을 입력할수없습니다");
      return;
    }
    out.writeUTF("ok");


  }

  private void listlesson() throws Exception {

    out.writeUTF("ok");
    out.reset();// 기존에 시리얼 라이즈 했던 객체의 상태를 무시하고 다시
    // 다시 시리얼 라이즈한다 .
    out.writeObject(lessonDao.findAll());


  }



  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }


}
