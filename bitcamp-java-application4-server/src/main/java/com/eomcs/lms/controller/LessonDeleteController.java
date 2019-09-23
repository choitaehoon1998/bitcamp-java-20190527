package com.eomcs.lms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.LessonDao;

@Component("/lesson/delete")
public class LessonDeleteController implements pageController {
  @Resource
  private LessonDao lessonDao;


  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws Exception {
    
      int no = Integer.parseInt(request.getParameter("no"));
      if (lessonDao.delete(no) == 0) {
        throw new Exception("해당 데이터가 없습니다.");
      }
      return"redirect:list";
  }
}












