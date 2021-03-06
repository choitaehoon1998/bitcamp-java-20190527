package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonListCommand implements Command {

  private LessonDao lessonDao;
  private Input input;

  public LessonListCommand(Input input,LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute() {
    try {
      List<Lesson>lessons = lessonDao.findAll();
      for (Lesson lesson :lessons) {
      System.out.printf("%s, %s, %s ~ %s, %s\n", lesson.getNo(), lesson.getTitle(),
          lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());
      }
    }catch(Exception e) {
      e.printStackTrace();
      
    }

   }
 }