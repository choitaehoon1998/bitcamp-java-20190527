package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.util.Input;

public class PhotoBoardDeleteCommand implements Command {

  private PhotoBoardDao photoBoardDao;

  public PhotoBoardDeleteCommand(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void execute(BufferedReader in , PrintStream out ) {

    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Board 객체를 찾는다.
    try {
      int no = Input.getIntValue(in,out,"번호? ");
      if (photoBoardDao.delete(no) > 0) {
        out.println("사진을 삭제했습니다");
      } else {
        out.println("해당데이터가없습니다");
      }
    } catch (Exception e) {
      out.println("데이터 삭제에 실패했습니다.");
      System.out.println(e.getMessage());
    }

  }

}
