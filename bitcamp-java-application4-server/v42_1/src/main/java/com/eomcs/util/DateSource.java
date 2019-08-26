package com.eomcs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

// Dao가 사용할 커넥션 객체를 생성해주는 역활
public class DateSource {
  String jdbcDriver;
  String jdbcUrl;
  String username;
  String password;

  //스레드 별로 커넥션 객체를 사용하도록
  //현재 스레드 값을 넣고 , 꺼낼 수 있는 도그를 준비한다   
  ThreadLocal<Connection> localConnection = new ThreadLocal<>();

  //재활용할 커넥션을 담을 바구니를 준비.
  ArrayList<Connection>conPool = new ArrayList<>();

  public DateSource(String jdbcDriver, String jdbcUrl, String username, String password) {
    this.jdbcDriver = jdbcDriver;
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection()throws Exception {
    //Thread 로컬 도구를 사용하여 현재 스레드에서 커넥션 객체를 꺼낸다 
    Connection con = localConnection.get();
    if(con == null) {// 없다면
      // 먼저 커넥션 풀에서 꺼낸다 
      if(conPool.size()>0) {//먼저 커넥션 풀에서 커넥션을찾아보고 있으면 꺼낸다 
        con = conPool.remove(0);
        System.out.println("기존커넥션 사용!");
      } else { // 커넥션풀에 없다면 새로 만든다 
        Class.forName(jdbcDriver);
        con = new TxConnection(DriverManager.getConnection(
            jdbcUrl, username, password));
        System.out.println("새커넥션 만들어사용 !");
      }
      
      //준비한 커넥션을 리턴하기전에  ThreadLocal 도구를 사용하여 스레드에 보관한다 
      localConnection.set(con);
    }

    return con;
  }
  //현재 스레드 보관된 커넥션 객체를 삭제한다 
  public void clearConnection() {
    Connection con = localConnection.get();
    if(con != null) {
      localConnection.remove();
      //스레드에서 제거한후 , 커넥션 객체를 다시 커넥션 풀에 저장한다
      conPool.add(con);
     System.out.println("커넥션풀에 다시저장 !");
    }

  }

}
