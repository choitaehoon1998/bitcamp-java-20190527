package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Lesson;
//DAO 사용규칙을 정의한다 
public interface LessonDao {
  int insert(Lesson lesson) throws Exception;
  public List<Lesson> findAll() throws Exception;
  public Lesson findBy(int no) throws Exception;
  public int update(Lesson lesson) throws Exception;
  public int delete(int no) throws Exception;
  
}
