package com.eomcs.util;

import java.sql.Connection;

// DB 커넥션 객체를 사용하여 트랜젝션을 관리하는 일을한다
public class PlatformTransactionManager {

  private DataSource dataSource;

  public PlatformTransactionManager(DataSource conFactory) {
    this.dataSource = conFactory;
  }

  public void beginTranSaction() throws Exception{
    //connectionFactory 는 커넥션 객체를 준비한후 
    //그커넥션 객체(의 주소)를 현재 스레드의 주머니에 보관하고,리턴한다 . 
    Connection con = dataSource.getConnection();
    con.setAutoCommit(false);
  }

  public void commit() throws Exception {
    // 현재 스레드의 주머니에서 커넥션 객체를 꺼낸다 . 
    Connection con = dataSource.getConnection();
    con.commit();
    con.setAutoCommit(true);
  }

  public void rollback()throws Exception {
    // 현재 스레드의 주머니에서 커넥션 객체를 꺼낸다 . 
    Connection con = dataSource.getConnection();
    con.rollback();
    con.setAutoCommit(true);
  }
}
