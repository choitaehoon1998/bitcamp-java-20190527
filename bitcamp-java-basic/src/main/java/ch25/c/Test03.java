// PreparedStatement를 이용하여 SQL 삽입 공격 차단하기
package ch25.c;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test03 {

  public static void main(String[] args)throws Exception {
   Connection con = DriverManager.getConnection(
       "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");
   PreparedStatement prst = con.prepareStatement(
       "update x_board set  contents =? ,title=? where board_id = ?");
   prst.setString(1, "1");
   prst.setString(2, "2");
   prst.setString(3, "3");
   int no =prst.executeUpdate();
   if(no ==0) {
     System.out.println("업데이트 실패");
   }else {
     System.out.println("성공");
   }
  
  }
 
 

}
