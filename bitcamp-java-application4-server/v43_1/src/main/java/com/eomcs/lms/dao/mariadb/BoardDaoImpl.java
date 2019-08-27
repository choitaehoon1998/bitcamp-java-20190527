package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDaoImpl implements BoardDao {

  SqlSessionFactory sqlSessionFactory;

  public BoardDaoImpl(SqlSessionFactory sqlSessionFactory) {

    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Board board) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();

    try {
      int count = sqlSession.insert("BoardDao.insert", board);
      sqlSession.commit();
      return count;

    } catch (Exception e) {
      sqlSession.rollback();
      throw e;

    } finally {
      sqlSession.close();
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("BoardDao.findAll");
    }
  }

  @Override
  public Board findBy(int no) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Board board = sqlSession.selectOne("BoardDao.findBy", no);
      if (board != null) {
        sqlSession.update("BoardDao.increaseViewCount", no);
        sqlSession.commit();
      }
      return board;

    } catch (Exception e) {
      sqlSession.rollback();
      throw e;

    } finally {
      sqlSession.close();
    }
  }


  @Override
  public int update(Board board) throws Exception {
    // opneSession을 호출할떄 다음과같이 autocommit 을 true 로 설정할수있따
    // 그러면commit 을 따로 호출하지않아도 자동으로commit할수있따
    try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
      return sqlSession.update("BoardDao.update", board);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
      return sqlSession.delete("BoardDao.delete", no);
    }
  }

}
