package com.eomcs.lms.handler;

import com.eomcs.lms.dao.LessonDao;
import com.eomcs.util.Input;

public class LessonDeleteCommand implements Command {

  private LessonDao lessonDao;
  private Input input;

  public LessonDeleteCommand(Input input,LessonDao lessonDao) {
    this.input = input;
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");

    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Lesson 객체를 찾는다.
    try {
      lessonDao.delete(no);
      System.out.println("데이터삭제");
    } catch (Exception e) {
      System.out.println("데이터 삭제에 실패했습니다.");
      System.out.println(e.getMessage());
    }

  }

}


