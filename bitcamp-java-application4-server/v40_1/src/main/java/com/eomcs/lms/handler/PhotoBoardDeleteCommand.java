package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Connection;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.util.ConnectionFactory;
import com.eomcs.util.Input;

public class PhotoBoardDeleteCommand implements Command {
  private ConnectionFactory conFactory;
  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;
  public PhotoBoardDeleteCommand(ConnectionFactory conFactory,PhotoBoardDao photoBoardDao,PhotoFileDao photoFileDao) {
    this.conFactory = conFactory ;
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao=photoFileDao;
  }

  @Override
  public void execute(BufferedReader in , PrintStream out ) {
    Connection con = null; 
    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Board 객체를 찾는다.
    try {
      con = conFactory.getConnection();
      con.setAutoCommit(false);
      
      
      int no = Input.getIntValue(in,out,"번호? ");

      if(photoBoardDao.findBy(no) == null) {
        out.println("해당데이터가없습니다");
        return;
      }

      //먼저 게시물의 첨부파일을 삭제한다
      photoFileDao.deleteAll(no);
      //게시물을 삭제한다 
      photoBoardDao.delete(no);
     
      
      con.commit();

      out.println("데이터를 삭제하였습니다");


    } catch (Exception e) {
      try {con.rollback();} catch (Exception e1) {}
      out.println("데이터 삭제에 실패했습니다.");
      System.out.println(e.getMessage());
      e.printStackTrace();
    }finally {
      
      try {con.setAutoCommit(true);} catch (Exception e3) {}
    }
  }

}
