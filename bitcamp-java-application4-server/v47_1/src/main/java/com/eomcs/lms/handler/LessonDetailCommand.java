package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Component;
import com.eomcs.util.Input;
@Component("/lesson/detail")
public class LessonDetailCommand implements Command{
  
  private LessonDao lessonDao;
  
  public LessonDetailCommand(LessonDao lessonDao) {
    this.lessonDao= lessonDao;
  }

  @Override
  public void execute(BufferedReader in , PrintStream out ) {
     try {
       int no = Input.getIntValue(in,out,"번호? ");
      Lesson lesson = lessonDao.findBy(no);
      if(lesson ==null) {
      out.println("해당번호의 데이터가없습니다");
      return;
      }
      out.printf("내용 :%s\n",lesson.getContents());
      out.printf("번호: %s\n",lesson.getNo());
      out.printf("제목 :%s\n",lesson.getTitle());
      out.printf("시작일: %s\n",lesson.getStartDate());
      out.printf("종료일:%s\n",lesson.getEndDate());
      out.printf("총시간: %s\n",lesson.getTotalHours());
      out.printf("일시간:%s\n",lesson.getDayHours());
    } catch (Exception e1) {
      out.println("데이터조회에 실패했습니다");
      e1.printStackTrace();
    }

      
    }


  }












