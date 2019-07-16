package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Input;

public class LessonHandler {

  public static Scanner keyScan;
  Input input;
  ArrayList LessonList = new ArrayList();

  public LessonHandler(Input i) {
    this.input = i;
  }

  public void addLesson() {
    // 수업 데이터를 저장할 메모리는 Lesson 설계도에 따라 만든다.
    Lesson lesson = new Lesson();

    // 사용자가 입력한 값을 Lesson 인스턴스의 각 변수에 저장한다.
    lesson.no = input.getIntValue("번호?");
    lesson.title = input.getStringValue("수업명? ");
    lesson.contents = input.getStringValue("설명? ");
    lesson.startDate = input.getDateValue("시작일? ");
    lesson.endDate = input.getDateValue("종료일? ");
    lesson.totalHours = input.getIntValue("총수업시간? ");
    lesson.dayHours = input.getIntValue("일수업시간? ");

    // 수업 데이터를 저장하고 있는 인스턴스의 주소를 레퍼런스 배열에 저장한다.
    LessonList.add(lesson);

    System.out.println("저장하였습니다.");
  }

  public void listLesson() {
    Object[] list = LessonList.toArray();

    for (Object obj : list) {
      Lesson lessons = (Lesson) obj;
      // 레퍼런스 배열에서 한 개의 인스턴스 주소를 꺼낸다.
      // 그 인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
      System.out.printf("%d, %s, %s ~ %s, %d\n", lessons.no, lessons.title, lessons.startDate,
          lessons.endDate, lessons.totalHours);
    }
  }



}
