// 트랜잭션 다루기 - mybatis + SqlSeSSIONfACTORYpROXY 으로 트렌젝션 제어하기
package ch26.i;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test04 {

  public static void main(String[] args) throws Exception {

    //
    InputStream inputStream = Resources.getResourceAsStream("ch26/i/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    //여러 메서드에서 같은 sqlSession 을 사용하는 방법 1
    // -> 멀티스레드 환경이라 가장하자
    // -> 같은 스레드의 경우SQLSession 을 사용하면 트랜젝션을 처리할수있따
    // 해결책
    // =>SQLSessionfactory 의 프록시를 만들어 sqlSession을 리턴할떄
    // 같은스레드에대해 같은 Sqlsession을 리턴하도록 한다 
    SqlSessionFactoryProxy SqlSessionFactoryProxy =
        new SqlSessionFactoryProxy(sqlSessionFactory);
    
     try {
    insert1(SqlSessionFactoryProxy);
    insert2(SqlSessionFactoryProxy);
     SqlSessionFactoryProxy.openSession().commit();
     }catch (Exception e) {
       SqlSessionFactoryProxy.openSession().rollback();
     }
     printList(SqlSessionFactoryProxy);
     //SqlSession 객체의 사용이 끝났으면 close한다
     // 단 실제 SqlSessionProxy로 형변환 한후 realclose()를 호출한다 .
     ((SqlSessionProxy)SqlSessionFactoryProxy.openSession()).realclose();
  }

  static void insert1(SqlSessionFactory sqlSessionFactory) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.insert("board.insert", new Board().setTitle("a107").setContents("내용7"));
    sqlSession.close();
  }

  static void insert2(SqlSessionFactory sqlSessionFactory) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    Board board = new Board();
    board.setTitle("a108");
    board.setContents("내용8");
    sqlSession.insert("board.insert", board);
    sqlSession.close();
  }
  static void printList(SqlSessionFactory sqlSessionFactory) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    List<Board> boards = sqlSession.selectList("board.select");
    for (Board b : boards) {
      System.out.println(b);
    }
    sqlSession.close();
  }
}


