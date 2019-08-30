package com.eomcs.util;

import java.sql.Connection;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
//sqlSessionfactory 로서 역활을 수행하기위해 인터페이스를 구현한다
// 실제작업은 원래 객체에게 맡긴다 
public class SqlSessionFactoryProxy implements SqlSessionFactory {
  SqlSessionFactory realFactory;
  //스레드 전용 바구니를 준비한다 . 
  // => 이바구니에는  SqlSession을 보관한다 
  ThreadLocal<SqlSession> localSqlSession =new ThreadLocal<>();


  public SqlSessionFactoryProxy(SqlSessionFactory realFactory) {
    this.realFactory =realFactory;
  }
  //스레드가 보관된 SqlSession을 제거한다 
  // => 클라이언트가 접속할떄 스레드를 만들고 , 응답을 완료할떄 스레드를 제거한다면
  //    굳이 스레드에 보관된 SqlSession을 제거할 필요가없다 .
  // => 스레드 풀을 사용할때는 스레드가 응답을 완료한 후 재사용 되기 때문에 ,
  //    다른 클라이언트의 요청을 처리하기위해 재사용 되기전에 
  //    이전에 사용했던 SqlSession을 제거해야한다 .
  //    왜? 이전에 사용했던 SqlSession은 트렌젝션을 종료하는 순간 close()되었기 때문이다 
  //    다시사용하고 싶어서 다시 사용할수없는 상태이다 
  public void clearSession() {
    localSqlSession.remove();
  }

  public void prepareSessionInThread() {
    SqlSession sqlSession = localSqlSession.get();

    if(sqlSession != null) //이미 스레드에 보관되어있다면 무시한다
      return;

    //아직 스레드에 SqlSession을 보관하지않았다면 
    // 실제 공장 객체를 통해 sqlsession을 만든후, 스레드에 보관한다 
    sqlSession = new SqlSessionProxy(realFactory.openSession());
    localSqlSession.set(sqlSession);
  }
  public SqlSession openSession() {
    SqlSession sqlSession = localSqlSession.get();
    //스레드에 sqlsession이 들어있다면 그것을 그대로 리턴한다 
    if(sqlSession !=null) 
      return sqlSession;

    //트렌젝션을 수행하지않는 상태라면 , 자동 커밋을 수행하는 SqlSession을 리턴한다 . 
    return realFactory.openSession(true);
  }
  public SqlSession openSession(boolean autoCommit) {
    return realFactory.openSession(autoCommit);
  }
  public SqlSession openSession(Connection connection) {
    return realFactory.openSession(connection);
  }
  public SqlSession openSession(TransactionIsolationLevel level) {
    return realFactory.openSession(level);
  }
  public SqlSession openSession(ExecutorType execType) {
    return realFactory.openSession(execType);
  }
  public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
    return realFactory.openSession(execType, autoCommit);
  }
  public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
    return realFactory.openSession(execType, level);
  }
  public SqlSession openSession(ExecutorType execType, Connection connection) {
    return realFactory.openSession(execType, connection);
  }
  public Configuration getConfiguration() {
    return realFactory.getConfiguration();
  }


}
