package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Component;
import com.eomcs.util.Input;
import com.eomcs.util.PlatformTransactionManager;
import com.eomcs.util.RequestMapping;
@Component
public class PhotoBoardCommand  {
  private PlatformTransactionManager txManager;
  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  public PhotoBoardCommand(PlatformTransactionManager txManager,PhotoBoardDao photoBoardDao,PhotoFileDao photoFileDao) {
    this.txManager = txManager ;
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }
  @RequestMapping("/photoboard/add")
  public void add(BufferedReader in , PrintStream out ) {
    try {
      txManager.beginTranSaction();

      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(Input.getStringValue(in,out,"제목? "));
      photoBoard.setLessonNo(Input.getIntValue(in,out,"수업? "));
      photoBoardDao.insert(photoBoard);


      out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
      out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");
      out.flush();
      int count = 0;
      while(true) {
        String filePath = Input.getStringValue(in,out,"사진 파일? ");
        if(filePath.length() == 0 ) {
          if(count>0) {
            break;
          }else {
            out.println ("최소 한개의 사진파일을 등록해야합니다. ");
            continue;
          }
        }
        PhotoFile photoFile = new PhotoFile();
        photoFile.setFilePath(filePath);
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
        count++;

      }
      // => DBMS 쪽 담당자(스레드)에게 임시보관된 데이터 변경 결과를 
      //    실제 테이블에 적용할것을 명령한다 . 
      txManager.commit();
      out.println("저장하였습니다.");

    } catch (Exception e) {
      // => DBMS 쪽 담당자(스레드)에게 임시보관된 데이터 변경 결과를 모두 취소할것을 명령한다 
      try {txManager.rollback();} catch (Exception e1) {}
      //예외가 발생하면 DBMS의 임시 데이터베이스에 보관된 데이터 변경 작업들을 모두 취소한다 . 
      out.println("데이터저장에 실패하였습니다");
      System.out.println(e.getMessage());
      e.printStackTrace();
    }

  }
  @RequestMapping("/photoboard/delete")
  public void delete(BufferedReader in , PrintStream out ) {
    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Board 객체를 찾는다.
    try {
      txManager.beginTranSaction();
      
      int no = Input.getIntValue(in,out,"번호? ");

      if(photoBoardDao.findBy(no) == null) {
        out.println("해당데이터가없습니다");
        return;
      }

      //먼저 게시물의 첨부파일을 삭제한다
      photoFileDao.deleteAll(no);
      //게시물을 삭제한다 
      photoBoardDao.delete(no);
     
      
      txManager.commit();

      out.println("데이터를 삭제하였습니다");


    } catch (Exception e) {
      try {txManager.rollback();} catch (Exception e1) {}
      out.println("데이터 삭제에 실패했습니다.");
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  @RequestMapping("/photoboard/detail")
  public void detail(BufferedReader in , PrintStream out ) {

    try {
      //클라이언트에게 번호를 요구하여 받는다. 
      int no = Input.getIntValue(in, out, "번호?");

      PhotoBoard photoBoard = photoBoardDao.findWithFilesBy(no);

      if(photoBoard ==null) {
        out.println("해당번호의 데이터가없습니다");
        return;
      }
      photoBoardDao.increaseViewCount(no);
      
      out.printf("제목 :%s\n",photoBoard.getTitle());
      out.printf("작성일: %s\n",photoBoard.getCreatedDate());
      out.printf("조회수: %s\n",photoBoard.getViewCount());
      out.printf("수업: %s\n",photoBoard.getLessonNo());
      out.println("사진파일:");

      List<PhotoFile>files = photoBoard.getFiles();
      for(PhotoFile file:files) {
        out.printf(">%s\n", file.getFilePath());
      }
    } catch (Exception e1) {
      out.println("데이터 조회에 실패했습니다.");
      System.out.println(e1.getMessage());
    }


  }

  @RequestMapping("/photoboard/list")
  public void list(BufferedReader in , PrintStream out ) {
    try {
      List<PhotoBoard>photoBoards = photoBoardDao.findAll();
      for (PhotoBoard photoBoard :photoBoards) { 
        out.printf("%d ,%-30s ,%s ,%d ,%d \n",
            photoBoard.getNo(),
            photoBoard.getTitle(),
            photoBoard.getCreatedDate(),
            photoBoard.getViewCount(),
            photoBoard.getLessonNo());
      }
    }catch(Exception e) {
      out.println("데이터 목록 조회에 실패하였습니다");
      System.out.println(e.getMessage());

    }

  }
  
  @RequestMapping("/photoboard/update")
  public void update(BufferedReader in, PrintStream out) {
    try {
      txManager.beginTranSaction();
      int no = Input.getIntValue(in, out, "번호? ");
      PhotoBoard photoBoard = photoBoardDao.findBy(no);
      if (photoBoard == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
      out.println("제목을 입력하지않으면 이전 제목을 유지합니다. ");
      String str = Input.getStringValue(in, out, String.format("제목(%s)? ", photoBoard.getTitle()));
      // 제목을 입력했다면 사진 게시글의 제목을 변경한다
      if (str.length() > 0) {
        photoBoard.setTitle(str);
        photoBoardDao.update(photoBoard);
        out.println("게시글의 제목을 변경하였습니다.");
      }

      // 이전에 등록한 파일목록을 출력한다
      out.println("사진파일:");
      List<PhotoFile> files = photoFileDao.findAll(no);
      for (PhotoFile file : files) {
        out.printf(">%s\n", file.getFilePath());
      }
      out.println("사진은 일부만 변경할 수 없습니다.");
      out.println("전체를 새로 등록해야 합니다.");
      String response = Input.getStringValue(in, out, "사진을 변경하겠습니까?(Y/N)");

      if (!response.equalsIgnoreCase("y")) {
        out.println("파일변경을 취소합니다.");
        txManager.commit();
        return;
      }
      // 기존 사진 파일을 삭제한다
      photoFileDao.deleteAll(no);

      out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
      out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");
      out.flush();

      int count = 0;
      while (true) {
        String filePath = Input.getStringValue(in, out, "사진 파일? ");
        if (filePath.length() == 0) {
          if (count > 0) {
            break;
          } else {
            out.println("최소 한개의 사진파일을 등록해야합니다. ");
            continue;
          }
        }
        PhotoFile photoFile = new PhotoFile();
        photoFile.setFilePath(filePath);
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
        count++;
      }
      txManager.commit();
      out.println("사진을 변경하였습니다.");


    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e1) {
      }
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    }



  }
  
}
