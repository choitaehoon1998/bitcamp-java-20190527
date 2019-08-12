package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonDetailCommand implements Command{
  
  private LessonDao lessonDao;
  private Input input;
  
  public LessonDetailCommand(Input input, LessonDao lessonDao) {
    this.input = input;
    this.lessonDao= lessonDao;
  }
  
  @Override
  public void execute() {
      int no = input.getIntValue("번호? ");
     try {
      Lesson lesson = lessonDao.findBy(no);
      if(lesson ==null) {
      System.out.println("해당번호의 데이터가없습니다");
      return;
      }
      System.out.printf("내용 :%s\n",lesson.getContents());
      System.out.printf("번호: %s\n",lesson.getNo());
      System.out.printf("제목 :%s\n",lesson.getTitle());
      System.out.printf("시작일: %s\n",lesson.getStartDate());
      System.out.printf("종료일:%s\n",lesson.getEndDate());
      System.out.printf("총시간: %s\n",lesson.getTotalHours());
      System.out.printf("일시간:%s\n",lesson.getDayHours());
    } catch (Exception e1) {
      
      e1.printStackTrace();
    }

      
    }


  }












