package com.eomcs.lms;

import java.sql.Date;

public class LessonTest {
  public static void main(String[] args) {
    // 수업데이터를 저장하기위해 낱개의 메모리를 준비해서 사용하는 경우
    int no = 1;
    String title = "자바";
    String contents = "자바개발";
    Date startDate = Date.valueOf("2019-1-1");
    Date endDate = Date.valueOf("2019-2-2");
    int totalHours = 100;
    int dayHours = 4;
    // 수업데이터를 저장하기위해 미리 준비한 메모리 설계도를 이용하는경우
    // => Lesson 메모리 설계도(클래스)에 따라 메모리를 준비하기.
    Lesson lesson = new Lesson();
    
    // ==> 레퍼런스에 저장된 주소로 찾아가서 메모리에 값 넣기
    // => Lesson lesson
    lesson.no = 1;
    lesson.title = "자바";
    lesson.contents = "자바개발";
    lesson.startDate = Date.valueOf("2019-1-1");
    lesson.endDate = Date.valueOf("2019-2-2");
    lesson.totalHours = 100;
    lesson.totalHours = 4;
    
    System.out.printf("&s ---&s\n",no ,lesson.no);
    System.out.printf("&s ---&s\n",title ,lesson.title);
    
  }
}
