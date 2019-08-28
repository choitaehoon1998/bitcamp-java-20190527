package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoImpl implements LessonDao {
  SqlSessionFactory sqlSessionFactory;
  
  public LessonDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory =sqlSessionFactory;
  }
  
  @Override
  public int insert(Lesson lesson) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.insert("LessonDao.insert",lesson);
      return count;
    }
  }

  @Override
  public List<Lesson> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("LessonDao.findAll");
    }
  }

  @Override
  public Lesson findBy(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();){
      Lesson lesson = sqlSession.selectOne("LessonDao.findBy", no);
      if (lesson != null) {
      }
      return lesson;

    }
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("LessonDao.update", lesson);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("LessonDao.delete", no);
    }
  }
}
