package com.eomcs.lms.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.domain.Lesson;

// 게시물 요청을 처리하는 담당자
public class LessonServlet implements Servlet {
  ArrayList<Lesson> lessonList = new ArrayList<>();
  ObjectInputStream in;
  ObjectOutputStream out;

  public LessonServlet(ObjectInputStream in, ObjectOutputStream out) throws ClassNotFoundException {
    this.in = in;
    this.out = out;
    
    try {
      loadData();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("수업 데이터 로딩 중 오류발생");
    }

  }
  @SuppressWarnings("unchecked")
  private void loadData() throws IOException, ClassNotFoundException {
    File file = new File("./lesson.ser");

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
      lessonList = (ArrayList<Lesson>) in.readObject();
      System.out.println("수업 데이터 로딩완료");
    }
  }

  public void saveData() {

    File file = new File("./lesson.ser");


    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
      out.writeObject(lessonList);
      System.out.println("수업 데이터 저장완료");

    } catch (FileNotFoundException e) {
      System.out.println("파일을 생성할 수 없습니다!");

    } catch (IOException e) {
      System.out.println("파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();

    }
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



  private void listlesson() throws Exception {

    out.writeUTF("ok");
    out.reset();// 기존에 시리얼 라이즈 했던 객체의 상태를 무시하고 다시
    // 다시 시리얼 라이즈한다 .
    out.writeObject(lessonList);

  }



  private void detaillesson() throws Exception {
    int no = in.readInt();
    int index = indexOfLesson(no);
    if (index == -1) {
      fail("해당번호의 회원이없습니다");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(lessonList.get(index));



  }



  private void deletelesson() throws Exception {
    int no = in.readInt();

    int index = indexOfLesson(no);
    if (index == -1) {
      fail("해당번호의 회원이없습니다");
      return;
    }
    lessonList.remove(index);
    out.writeUTF("ok");

  }



  private void updatelesson() throws Exception {
    Lesson lesson = (Lesson) in.readObject();
    for (Lesson m : lessonList) {
      if (m.getNo() == lesson.getNo()) {
        m.setTitle(lesson.getTitle());
        m.setStartDate(lesson.getStartDate());
        m.setDayHours(lesson.getDayHours());
        m.setEndDate(lesson.getEndDate());
        m.setContents(lesson.getContents());
        m.setTotalHours(lesson.getTotalHours());
        out.writeUTF("ok");
        return;
      }
    }
    fail("해당번호의 회원이없습니다");

  }



  private void addlesson() throws Exception {
    Lesson lesson = (Lesson) in.readObject();
    lessonList.add(lesson);
    out.writeUTF("ok");


  }

  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

  private int indexOfLesson(int no) {
    int i = 0;
    for (Lesson l : lessonList) {
      if (l.getNo() == no) {
        return i;
      }
      i++;
    }
    return -1;
  }

}
