package com.eomcs.lms;

import java.util.Map;
import com.eomcs.lms.context.ServletContextListener;
import com.eomcs.lms.dao.serial.BoardSerialDao;
import com.eomcs.lms.dao.serial.LessonSerialDao;
import com.eomcs.lms.dao.serial.MemberSerialDao;

// 서버가 시작되거나 종료될때 보고를 받고 작업을 수행하는 역활을 한다 .
// => ServletContextListener 규칙을 준비해야만 서버의 시작과 종료알림을 받을수있다
public class ApplicationInitListener implements ServletContextListener {
  BoardSerialDao boardDao;
  MemberSerialDao memberDao;
  LessonSerialDao lessonDao;

 public void contextInitialized(Map<String,Object> context) {
    System.out.println("서버를 시작했으니 객체를 준비해야겠다");
 
 try { boardDao = new BoardSerialDao("./board.ser");
     context.put("boardDao",boardDao );
 
 }catch(Exception e) {
   System.out.println("데이터로딩중 오류발생 ");
 }
 try{memberDao = new MemberSerialDao("./member.ser");
 context.put("memberDao",memberDao );
 }catch(Exception e) {
   System.out.println("데이터로딩중 오래발생");
 }
 try{lessonDao = new LessonSerialDao("./lesson.ser");
 context.put("lessonDao",lessonDao );
 }catch(Exception e) {
   System.out.println("데이터로딩중 오래발생");
 }
  // BoardCsvDao boardDao = new BoardCsvDao("./board.csv");
  // MemberCsvDao memberDao = new MemberCsvDao("./member.csv");
  // LessonCsvDao lessonDao = new LessonCsvDao("./lesson.csv");

 }

  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("서버가 종료되니 자원을 해제해야겠따");
    boardDao.saveData();
    lessonDao.saveData();
    memberDao.saveData();
  }
}
